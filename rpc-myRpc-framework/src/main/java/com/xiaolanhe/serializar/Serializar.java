package com.xiaolanhe.serializar;

import com.xiaolanhe.protocol.Protocol;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package serializar
 * @date 2024/2/7 10:42
 */
public interface Serializar {

    byte[] serializar(Protocol protocol) throws Exception;

    Protocol deserializar(byte[] bytes) throws Exception;
}
