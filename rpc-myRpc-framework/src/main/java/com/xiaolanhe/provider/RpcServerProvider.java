package com.xiaolanhe.provider;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:56
 */

import com.xiaolanhe.channelInitializer.RpcServerProviderInitializar;
import com.xiaolanhe.common.HostAndPort;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import com.xiaolanhe.registry.Registry;

import java.net.InetAddress;
import java.util.Map;
import java.util.Set;

/**
 *  1.构建netty服务端
 *  2.引入注册中心
 *  3.引入所有的业务对象
 *  4.通过反射进行调用
 *
 * */
public class RpcServerProvider {

    private int port;

    private EventLoopGroup eventLoopGroupBoss;

    private EventLoopGroup eventLoopGroupWorker;

    /**
     * netty 的编解码器  内置handler 通过这个线程组服务 (无需考虑 io)
     */
    private EventLoopGroup eventLoopGroupHandler;

    /**
     * service 等通过这个线程组服务 (无需考虑 io)
     */
    private EventLoopGroup eventLoopGroupService;

    private int workerThreads;
    private int handlerThreads;
    private int serviceThreads;

    private Registry registry;

    private ServerBootstrap serverBootstrap;

    /**
     *  key 为服务名
     *  value 为服务对象
     * */
    private Map<String, Object> exposeBeans;

    private boolean isStarted = false;

    public RpcServerProvider(Registry registry, Map<String, Object> exposeBeans)
    {
        this(8080, 1, 1, 1, registry, exposeBeans);
    }

    public RpcServerProvider(int port, Registry registry, Map<String, Object> exposeBeans){
        this(port, 1, 1, 1, registry, exposeBeans);
    }

    public RpcServerProvider(int port, int workerThreads, int handlerThreads, int serviceThreads, Registry registry, Map<String, Object> exposeBeans) {
        this.port = port;
        this.workerThreads = workerThreads;
        this.handlerThreads = handlerThreads;
        this.serviceThreads = serviceThreads;

        this.eventLoopGroupBoss = new NioEventLoopGroup(1);
        this.eventLoopGroupWorker = new NioEventLoopGroup(this.workerThreads);
        this.eventLoopGroupHandler = new DefaultEventLoopGroup(this.handlerThreads);
        this.eventLoopGroupService = new DefaultEventLoopGroup(this.serviceThreads);

        this.registry = registry;
        this.exposeBeans = exposeBeans;

        this.serverBootstrap = new ServerBootstrap();
    }

    public void startServer() throws InterruptedException {
        if(isStarted){
            throw new RuntimeException("server is already started....");
        }

        // 1. netty服务端开发
        serverBootstrap.group(eventLoopGroupBoss, eventLoopGroupWorker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new RpcServerProviderInitializar(this.eventLoopGroupHandler, this.eventLoopGroupService, exposeBeans));

        // 原因：这样采用阻塞方式完成服务端口的绑定，导致后面的代码永远不能被执行
        /** Channel channel = serverBootstrap.bind(port).sync().channel();
         channel.closeFuture().sync(); */

        ChannelFuture channelFuture = serverBootstrap.bind(port);
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            // 端口绑定完成
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    // 2. 完成服务的注册
                    registerServices(InetAddress.getLocalHost().getHostAddress(), port, exposeBeans, registry);
                    isStarted = true;

                    // 3. 关闭监听
                    Channel channel = channelFuture.channel();
                    ChannelFuture closeFuture = channel.closeFuture();

                    closeFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> future) throws Exception {
                            if(future.isSuccess()){
                                closeServer();
                            }
                        }
                    });
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread( () -> closeServer()));
    }

    private void registerServices(String ip, int port, Map<String, Object> exposeBeans, Registry registry) {
        // 获取所有的服务名
        Set<String> keySet = exposeBeans.keySet();
        // 遍历服务对象名，通过registry进行注册
        HostAndPort hostAndPort = new HostAndPort(ip, port);
        for(String targetInterface : keySet){
            registry.registerService(targetInterface, hostAndPort);
        }
    }

    /**
     *  关闭服务，释放资源
     */
    public void closeServer()
    {
        eventLoopGroupBoss.shutdownGracefully();
        eventLoopGroupWorker.shutdownGracefully();
        eventLoopGroupHandler.shutdownGracefully();
        eventLoopGroupService.shutdownGracefully();
    }
}
