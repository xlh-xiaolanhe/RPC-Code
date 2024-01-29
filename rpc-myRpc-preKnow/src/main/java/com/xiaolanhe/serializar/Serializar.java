package com.xiaolanhe.serializar;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/29 23:48
 */
public interface Serializar {
    byte[] serializar(Object object) throws Exception;

    Object deserializar(byte[] bytes) throws Exception;
}
