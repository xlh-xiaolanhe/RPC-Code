package com.xiaolanhe.serializar.impl;

import com.xiaolanhe.serializar.Serializar;
import com.xiaolanhe.serializar.User;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 0:25
 */
public class ThriftSerializar implements Serializar {
    @Override
    public byte[] serializar(Object object) throws Exception {
        User user = (User) object;
        // 转换成 thrift 能识别的类
        com.xiaolanhe.thrift.User u = new com.xiaolanhe.thrift.User();
        u.setName(user.getName());

        // 转换成字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TBinaryProtocol binaryProtocol = new TBinaryProtocol(new TIOStreamTransport(outputStream));
        u.write(binaryProtocol);
        return outputStream.toByteArray();
    }

    @Override
    public Object deserializar(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        TBinaryProtocol binaryProtocol = new TBinaryProtocol(new TIOStreamTransport(inputStream));
        com.xiaolanhe.thrift.User user = new com.xiaolanhe.thrift.User();
        user.read(binaryProtocol);

        // 转换成 java 能识别的类
        User u = new User();
        u.setName(user.getName());
        return u;
    }
}
