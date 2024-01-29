package com.xiaolanhe.serializar.impl;

import com.xiaolanhe.HelloProto;
import com.xiaolanhe.serializar.Serializar;
import com.xiaolanhe.serializar.User;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 0:34
 */
public class ProtoBufSerializar implements Serializar {
    @Override
    public byte[] serializar(Object object) throws Exception {
        User user = (User) object;

        HelloProto.HelloRequest helloRequest = HelloProto.HelloRequest.newBuilder().setName(user.getName()).build();

        return helloRequest.toByteArray();
    }

    @Override
    public Object deserializar(byte[] bytes) throws Exception {
        HelloProto.HelloRequest helloRequest = HelloProto.HelloRequest.parseFrom(bytes);
        User user = new User();
        user.setName(helloRequest.getName());
        return user;
    }
}
