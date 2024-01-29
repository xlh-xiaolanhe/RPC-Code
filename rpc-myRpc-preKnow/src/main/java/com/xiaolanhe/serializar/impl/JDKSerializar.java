package com.xiaolanhe.serializar.impl;

import com.xiaolanhe.serializar.Serializar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/29 23:53
 */
public class JDKSerializar implements Serializar {
    @Override
    public byte[] serializar(Object object) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        return outputStream.toByteArray();
    }

    @Override
    public Object deserializar(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }
}
