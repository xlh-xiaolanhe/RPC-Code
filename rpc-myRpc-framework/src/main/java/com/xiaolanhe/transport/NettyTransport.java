package com.xiaolanhe.transport;

import com.xiaolanhe.channelInitializer.RpcClientChannelInitializer;
import com.xiaolanhe.common.HostAndPort;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:45
 */
public class NettyTransport implements Transport{

    private Bootstrap bootstrap;

    private EventLoopGroup worker;

    private int workedThreads;

    public NettyTransport()
    {
        this(1);
    }

    public NettyTransport(int workedThreads){
        this.workedThreads = workedThreads;

        bootstrap = new Bootstrap();
        worker = new NioEventLoopGroup(this.workedThreads);
        bootstrap.group(worker);

        bootstrap.channel(NioSocketChannel.class);
    }

    @Override
    public Result invoke(HostAndPort hostAndPort, MethodInvokeData methodInvokeData) throws Exception {
        RpcClientChannelInitializer rpcClientChannelInitializer = new RpcClientChannelInitializer(methodInvokeData);
        bootstrap.handler(rpcClientChannelInitializer);

        ChannelFuture channelFuture = bootstrap.connect(hostAndPort.getHostName(), hostAndPort.getPort()).sync();
        // 等待与服务器的连接断开，期间程序保持运行
        channelFuture.channel().closeFuture().sync();
        return rpcClientChannelInitializer.getResult();
    }

    @Override
    public void close() {
        worker.shutdownGracefully();
    }
}
