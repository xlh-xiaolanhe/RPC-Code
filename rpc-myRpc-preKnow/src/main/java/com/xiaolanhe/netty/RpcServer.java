package com.xiaolanhe.netty;

import com.xiaolanhe.serializar.Serializar;
import com.xiaolanhe.serializar.User;
import com.xiaolanhe.serializar.impl.JDKSerializar;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 23:12
 */
public class RpcServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        /*双参数 group(parent, children): 在这种形式下，第一个 EventLoopGroup (parent) 专门用于接受新的连接，而第二个 EventLoopGroup (children) 用于处理已经接受的连接的 IO 事件。*/
        serverBootstrap.group(new NioEventLoopGroup());

        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 0));
                ch.pipeline().addLast(new LoggingHandler());
                ch.pipeline().addLast(new RPCMessageToMessageCodec(new JDKSerializar()));
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        User user = (User) msg;
                        System.out.println("user = " + user);
                    }
                });
            }
        });
        serverBootstrap.bind(8000);
    }
}
