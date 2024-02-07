package com.xiaolanhe.channelInitializer;

import com.xiaolanhe.codec.RPCMessageToMessageCodec;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;
import com.xiaolanhe.serializar.HessianSerializar;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:50
 */
@Slf4j
@Data
public class RpcClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private MethodInvokeData methodInvokeData;

    private Result result;

    public RpcClientChannelInitializer(MethodInvokeData methodInvokeData)
    {
        this.methodInvokeData = methodInvokeData;
    }

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline pipeline = nioSocketChannel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 6, 1, 0, 0));
        pipeline.addLast(new LoggingHandler());
        pipeline.addLast(new RPCMessageToMessageCodec(new HessianSerializar()));
        pipeline.addLast(new ChannelInboundHandlerAdapter(){
            // client 向 server 发送请求

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                log.debug("begin send request to server... {}", methodInvokeData);
                ChannelFuture channelFuture = ctx.writeAndFlush(methodInvokeData);
                // 发生异常就关闭channel
                channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }

            // server 回复 client
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("receive response from server...{}", msg);
                result = (Result) msg;
            }
        });
    }
}
