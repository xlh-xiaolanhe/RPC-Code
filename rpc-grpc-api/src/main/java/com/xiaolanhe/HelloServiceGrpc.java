package com.xiaolanhe;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * 定义服务接口
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: Hello.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloServiceGrpc {

  private HelloServiceGrpc() {}

  public static final String SERVICE_NAME = "HelloService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hello",
      requestType = com.xiaolanhe.HelloProto.HelloRequest.class,
      responseType = com.xiaolanhe.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getHelloMethod() {
    io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse> getHelloMethod;
    if ((getHelloMethod = HelloServiceGrpc.getHelloMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getHelloMethod = HelloServiceGrpc.getHelloMethod) == null) {
          HelloServiceGrpc.getHelloMethod = getHelloMethod =
              io.grpc.MethodDescriptor.<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "hello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("hello"))
              .build();
        }
      }
    }
    return getHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequestMultiValue,
      com.xiaolanhe.HelloProto.HelloResponseMultiValue> getHello2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hello2",
      requestType = com.xiaolanhe.HelloProto.HelloRequestMultiValue.class,
      responseType = com.xiaolanhe.HelloProto.HelloResponseMultiValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequestMultiValue,
      com.xiaolanhe.HelloProto.HelloResponseMultiValue> getHello2Method() {
    io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequestMultiValue, com.xiaolanhe.HelloProto.HelloResponseMultiValue> getHello2Method;
    if ((getHello2Method = HelloServiceGrpc.getHello2Method) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getHello2Method = HelloServiceGrpc.getHello2Method) == null) {
          HelloServiceGrpc.getHello2Method = getHello2Method =
              io.grpc.MethodDescriptor.<com.xiaolanhe.HelloProto.HelloRequestMultiValue, com.xiaolanhe.HelloProto.HelloResponseMultiValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "hello2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloRequestMultiValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloResponseMultiValue.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("hello2"))
              .build();
        }
      }
    }
    return getHello2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getClient2ServerStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "client2ServerStream",
      requestType = com.xiaolanhe.HelloProto.HelloRequest.class,
      responseType = com.xiaolanhe.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getClient2ServerStreamMethod() {
    io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse> getClient2ServerStreamMethod;
    if ((getClient2ServerStreamMethod = HelloServiceGrpc.getClient2ServerStreamMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getClient2ServerStreamMethod = HelloServiceGrpc.getClient2ServerStreamMethod) == null) {
          HelloServiceGrpc.getClient2ServerStreamMethod = getClient2ServerStreamMethod =
              io.grpc.MethodDescriptor.<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "client2ServerStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("client2ServerStream"))
              .build();
        }
      }
    }
    return getClient2ServerStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getClientStream2ServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clientStream2Server",
      requestType = com.xiaolanhe.HelloProto.HelloRequest.class,
      responseType = com.xiaolanhe.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getClientStream2ServerMethod() {
    io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse> getClientStream2ServerMethod;
    if ((getClientStream2ServerMethod = HelloServiceGrpc.getClientStream2ServerMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getClientStream2ServerMethod = HelloServiceGrpc.getClientStream2ServerMethod) == null) {
          HelloServiceGrpc.getClientStream2ServerMethod = getClientStream2ServerMethod =
              io.grpc.MethodDescriptor.<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "clientStream2Server"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("clientStream2Server"))
              .build();
        }
      }
    }
    return getClientStream2ServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getClientStream2ServerStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clientStream2ServerStream",
      requestType = com.xiaolanhe.HelloProto.HelloRequest.class,
      responseType = com.xiaolanhe.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest,
      com.xiaolanhe.HelloProto.HelloResponse> getClientStream2ServerStreamMethod() {
    io.grpc.MethodDescriptor<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse> getClientStream2ServerStreamMethod;
    if ((getClientStream2ServerStreamMethod = HelloServiceGrpc.getClientStream2ServerStreamMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getClientStream2ServerStreamMethod = HelloServiceGrpc.getClientStream2ServerStreamMethod) == null) {
          HelloServiceGrpc.getClientStream2ServerStreamMethod = getClientStream2ServerStreamMethod =
              io.grpc.MethodDescriptor.<com.xiaolanhe.HelloProto.HelloRequest, com.xiaolanhe.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "clientStream2ServerStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaolanhe.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("clientStream2ServerStream"))
              .build();
        }
      }
    }
    return getClientStream2ServerStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceStub>() {
        @java.lang.Override
        public HelloServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceStub(channel, callOptions);
        }
      };
    return HelloServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingStub>() {
        @java.lang.Override
        public HelloServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceBlockingStub(channel, callOptions);
        }
      };
    return HelloServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceFutureStub>() {
        @java.lang.Override
        public HelloServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceFutureStub(channel, callOptions);
        }
      };
    return HelloServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * 定义服务接口
   * </pre>
   */
  public static abstract class HelloServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void hello(com.xiaolanhe.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHelloMethod(), responseObserver);
    }

    /**
     */
    public void hello2(com.xiaolanhe.HelloProto.HelloRequestMultiValue request,
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponseMultiValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHello2Method(), responseObserver);
    }

    /**
     */
    public void client2ServerStream(com.xiaolanhe.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClient2ServerStreamMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloRequest> clientStream2Server(
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getClientStream2ServerMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloRequest> clientStream2ServerStream(
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getClientStream2ServerStreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHelloMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xiaolanhe.HelloProto.HelloRequest,
                com.xiaolanhe.HelloProto.HelloResponse>(
                  this, METHODID_HELLO)))
          .addMethod(
            getHello2Method(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xiaolanhe.HelloProto.HelloRequestMultiValue,
                com.xiaolanhe.HelloProto.HelloResponseMultiValue>(
                  this, METHODID_HELLO2)))
          .addMethod(
            getClient2ServerStreamMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.xiaolanhe.HelloProto.HelloRequest,
                com.xiaolanhe.HelloProto.HelloResponse>(
                  this, METHODID_CLIENT2SERVER_STREAM)))
          .addMethod(
            getClientStream2ServerMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.xiaolanhe.HelloProto.HelloRequest,
                com.xiaolanhe.HelloProto.HelloResponse>(
                  this, METHODID_CLIENT_STREAM2SERVER)))
          .addMethod(
            getClientStream2ServerStreamMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.xiaolanhe.HelloProto.HelloRequest,
                com.xiaolanhe.HelloProto.HelloResponse>(
                  this, METHODID_CLIENT_STREAM2SERVER_STREAM)))
          .build();
    }
  }

  /**
   * <pre>
   * 定义服务接口
   * </pre>
   */
  public static final class HelloServiceStub extends io.grpc.stub.AbstractAsyncStub<HelloServiceStub> {
    private HelloServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceStub(channel, callOptions);
    }

    /**
     */
    public void hello(com.xiaolanhe.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hello2(com.xiaolanhe.HelloProto.HelloRequestMultiValue request,
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponseMultiValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHello2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void client2ServerStream(com.xiaolanhe.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getClient2ServerStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloRequest> clientStream2Server(
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getClientStream2ServerMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloRequest> clientStream2ServerStream(
        io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getClientStream2ServerStreamMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * 定义服务接口
   * </pre>
   */
  public static final class HelloServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<HelloServiceBlockingStub> {
    private HelloServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xiaolanhe.HelloProto.HelloResponse hello(com.xiaolanhe.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xiaolanhe.HelloProto.HelloResponseMultiValue hello2(com.xiaolanhe.HelloProto.HelloRequestMultiValue request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHello2Method(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.xiaolanhe.HelloProto.HelloResponse> client2ServerStream(
        com.xiaolanhe.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getClient2ServerStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 定义服务接口
   * </pre>
   */
  public static final class HelloServiceFutureStub extends io.grpc.stub.AbstractFutureStub<HelloServiceFutureStub> {
    private HelloServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xiaolanhe.HelloProto.HelloResponse> hello(
        com.xiaolanhe.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xiaolanhe.HelloProto.HelloResponseMultiValue> hello2(
        com.xiaolanhe.HelloProto.HelloRequestMultiValue request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHello2Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HELLO = 0;
  private static final int METHODID_HELLO2 = 1;
  private static final int METHODID_CLIENT2SERVER_STREAM = 2;
  private static final int METHODID_CLIENT_STREAM2SERVER = 3;
  private static final int METHODID_CLIENT_STREAM2SERVER_STREAM = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HELLO:
          serviceImpl.hello((com.xiaolanhe.HelloProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse>) responseObserver);
          break;
        case METHODID_HELLO2:
          serviceImpl.hello2((com.xiaolanhe.HelloProto.HelloRequestMultiValue) request,
              (io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponseMultiValue>) responseObserver);
          break;
        case METHODID_CLIENT2SERVER_STREAM:
          serviceImpl.client2ServerStream((com.xiaolanhe.HelloProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLIENT_STREAM2SERVER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clientStream2Server(
              (io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse>) responseObserver);
        case METHODID_CLIENT_STREAM2SERVER_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clientStream2ServerStream(
              (io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xiaolanhe.HelloProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloService");
    }
  }

  private static final class HelloServiceFileDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier {
    HelloServiceFileDescriptorSupplier() {}
  }

  private static final class HelloServiceMethodDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloServiceFileDescriptorSupplier())
              .addMethod(getHelloMethod())
              .addMethod(getHello2Method())
              .addMethod(getClient2ServerStreamMethod())
              .addMethod(getClientStream2ServerMethod())
              .addMethod(getClientStream2ServerStreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
