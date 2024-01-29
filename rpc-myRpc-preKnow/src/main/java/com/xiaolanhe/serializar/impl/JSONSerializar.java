package com.xiaolanhe.serializar.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaolanhe.serializar.Serializar;
import com.xiaolanhe.serializar.User;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 0:00
 */
public class JSONSerializar implements Serializar {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serializar(Object object) throws Exception {
        return objectMapper.writeValueAsBytes(object);
    }

    @Override
    public Object deserializar(byte[] bytes) throws Exception {
        return objectMapper.readValue(bytes, User.class);
    }
}
