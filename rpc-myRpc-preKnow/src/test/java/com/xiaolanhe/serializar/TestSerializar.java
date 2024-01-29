package com.xiaolanhe.serializar;

import com.xiaolanhe.serializar.impl.HessianSerializar;
import com.xiaolanhe.serializar.impl.JDKSerializar;
import com.xiaolanhe.serializar.impl.JSONSerializar;
import com.xiaolanhe.serializar.impl.ProtoBufSerializar;
import com.xiaolanhe.serializar.impl.ThriftSerializar;
import org.junit.Test;

import java.sql.SQLOutput;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 0:38
 */


public class TestSerializar {

    @Test
    public void test() throws Exception {
        User user = new User("xiaolanhe");
        System.out.println("json serializer length: " + testSerializar(new JSONSerializar(), user));
        System.out.println("protobuf serializer length: " + testSerializar(new ProtoBufSerializar(), user));
        System.out.println("jdk serializer length: " + testSerializar(new JDKSerializar(), user));
        System.out.println("thrift serializer length: " + testSerializar(new ThriftSerializar(), user));
        System.out.println("hessian serializer length: " + testSerializar(new HessianSerializar(), user));

        /**
        json serializer length: 20
        protobuf serializer length: 11
        jdk serializer length: 90
        thrift serializer length: 17
        hessian serializer length: 48
        */
    }

    public int testSerializar(Serializar serializar, User user) throws Exception {
        return serializar.serializar(user).length;
    }
}
