package com.xiaolanhe.serializar;

import com.xiaolanhe.protocol.Protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:43
 */
public class JDKSerializar implements Serializar{
    @Override
    public byte[] serializar(Protocol protocol) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(protocol);
        return outputStream.toByteArray();
    }

    @Override
    public Protocol deserializar(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (Protocol) objectInputStream.readObject();
    }
}
