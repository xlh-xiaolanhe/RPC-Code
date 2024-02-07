package com.xiaolanhe.transport;

import com.xiaolanhe.common.HostAndPort;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package transprot
 * @date 2024/2/7 10:44
 */
public interface Transport {

    Result invoke(HostAndPort hostAndPort, MethodInvokeData methodInvokeData) throws Exception;

    void close();
}
