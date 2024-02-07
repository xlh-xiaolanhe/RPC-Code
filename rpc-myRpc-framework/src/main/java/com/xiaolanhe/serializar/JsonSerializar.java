package com.xiaolanhe.serializar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaolanhe.protocol.Protocol;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:43
 */
public class JsonSerializar implements Serializar{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serializar(Protocol protocol) throws Exception {
        return objectMapper.writeValueAsBytes(protocol);
    }

    @Override
    public Protocol deserializar(byte[] bytes) throws Exception {
        return objectMapper.readValue(bytes, Protocol.class);
    }
}
