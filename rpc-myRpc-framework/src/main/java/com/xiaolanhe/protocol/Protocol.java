package com.xiaolanhe.protocol;

import java.io.Serializable;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package protocol
 * @date 2024/2/7 10:38
 */
public interface Protocol extends Serializable {
    String MAGIC_NUMBER = "myrpc";
    byte PROTOCOL_VERSION = 1;
}
