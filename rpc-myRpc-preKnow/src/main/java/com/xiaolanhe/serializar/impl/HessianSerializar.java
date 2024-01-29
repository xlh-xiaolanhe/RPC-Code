package com.xiaolanhe.serializar.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.xiaolanhe.serializar.Serializar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 0:16
 */
public class HessianSerializar implements Serializar {
    @Override
    public byte[] serializar(Object object) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(outputStream);
        hessian2Output.writeObject(object);
        hessian2Output.flush();
        return outputStream.toByteArray();
    }

    @Override
    public Object deserializar(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(inputStream);
        return hessian2Input.readObject();
    }
}
