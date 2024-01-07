package com.xiaolanhe.streamTracer;

import io.grpc.Metadata;
import io.grpc.ServerStreamTracer;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:56
 */
public class CustomServerStreamFactory extends ServerStreamTracer.Factory {
    @Override
    public ServerStreamTracer newServerStreamTracer(String fullMethodName, Metadata headers) {
        return new CustomServerStreamTracer();
    }
}
