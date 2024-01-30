package com.xiaolanhe.netty;

import com.xiaolanhe.serializar.User;
import com.xiaolanhe.serializar.impl.JDKSerializar;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 23:12
 */

@Slf4j
public class RpcClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(eventLoopGroup);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 0));
                ch.pipeline().addLast(new LoggingHandler());
                ch.pipeline().addLast(new RPCMessageToMessageCodec(new JDKSerializar()));
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        // User对象 POJO
                        ctx.writeAndFlush(new User("xiaohei"));
                    }
                });
            }
        });

        ChannelFuture connect = bootstrap.connect(new InetSocketAddress(8000));
        connect.sync();
    }
}
