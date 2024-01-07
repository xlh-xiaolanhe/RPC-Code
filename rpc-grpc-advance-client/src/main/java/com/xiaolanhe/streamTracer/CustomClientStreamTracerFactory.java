package com.xiaolanhe.streamTracer;

import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:47
 */
public class CustomClientStreamTracerFactory<ReqT, RespT> extends ClientStreamTracer.Factory {
    @Override
    public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo info, Metadata headers) {
        return new CustomClientStreamTracer<>();
    }
}
