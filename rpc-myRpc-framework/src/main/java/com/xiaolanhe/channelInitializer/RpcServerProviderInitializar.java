package com.xiaolanhe.channelInitializer;

import com.xiaolanhe.codec.RPCMessageToMessageCodec;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;
import com.xiaolanhe.serializar.HessianSerializar;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:51
 */
@Slf4j
public class RpcServerProviderInitializar extends ChannelInitializer<NioSocketChannel> {

    /**
     * 为内置的编解码器，handler 服务的线程组
     */
    private EventLoopGroup eventLoopGroupHandler;

    /**
     * 为 service服务的线程组
     */
    private EventLoopGroup eventLoopGroupService;

    private Map<String, Object> exposeBeans;

    public RpcServerProviderInitializar(EventLoopGroup eventLoopGroupHandler, EventLoopGroup eventLoopGroupService, Map<String, Object> exposeBeans) {
        this.eventLoopGroupHandler = eventLoopGroupHandler;
        this.eventLoopGroupService = eventLoopGroupService;
        this.exposeBeans = exposeBeans;
    }

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(this.eventLoopGroupHandler, new LengthFieldBasedFrameDecoder(1024, 6, 4, 0, 0));

        pipeline.addLast(this.eventLoopGroupHandler, new LoggingHandler());

        pipeline.addLast(this.eventLoopGroupService, new RPCMessageToMessageCodec(new HessianSerializar()));

        //RPC功能的调用
        pipeline.addLast(this.eventLoopGroupService, new ChannelInboundHandlerAdapter(){
            //接收到客户端发送来的请求
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                // client提交的 用于进行rpc调用的相关参数
                MethodInvokeData methodInvokeData = (MethodInvokeData) msg;

                // 完成 rpc 调用
                Result result = executeInvokeTargetObject(methodInvokeData, exposeBeans);

                //响应请求
                ChannelFuture channelFuture = ctx.writeAndFlush(result);

                channelFuture.addListener(ChannelFutureListener.CLOSE);
                channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        });


    }

    /**
     * 完成 rpc 的调用
     * @param methodInvokeData
     * @param exposeBeans
     * @return
     */
    private Result executeInvokeTargetObject(MethodInvokeData methodInvokeData, Map<String, Object> exposeBeans) throws NoSuchMethodException {
        log.debug("ExecuteInvokeTargetObject...{}", methodInvokeData);

        Class<?> targetInterface = methodInvokeData.getTargetInterface();

        // 获取对应接口的实现对象
        Object nativeObject = exposeBeans.get(targetInterface.getName());

        // 获取接口中需要调用的方法 方法名 方法参数类型
        Method invokeMethod = targetInterface.getDeclaredMethod(methodInvokeData.getMethodName(), methodInvokeData.getParametersTypes());

        Result result = new Result();

        try{
            Object ret = invokeMethod.invoke(nativeObject, methodInvokeData.getParameters());
            log.debug("{} invoke returnValue is {} ", invokeMethod , ret);
            result.setResultValue(ret);
        }catch (Exception e){
            log.error("methond invoke error", e);
            result.setException(e);
        }
        return result;
    }
}
