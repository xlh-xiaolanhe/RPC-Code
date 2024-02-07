package com.xiaolanhe.serializar;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.xiaolanhe.protocol.Protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:44
 */
public class HessianSerializar implements Serializar{
    @Override
    public byte[] serializar(Protocol protocol) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(outputStream);
        hessian2Output.writeObject(protocol);
        hessian2Output.flush();
        return outputStream.toByteArray();
    }

    @Override
    public Protocol deserializar(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(inputStream);
        return (Protocol) hessian2Input.readObject();
    }
}
