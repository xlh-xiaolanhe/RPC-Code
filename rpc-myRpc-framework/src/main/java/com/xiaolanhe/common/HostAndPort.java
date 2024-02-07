package com.xiaolanhe.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:36
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HostAndPort {
    private String hostName;
    private int port;
}
