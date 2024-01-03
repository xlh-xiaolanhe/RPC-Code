package com.xiaolanhe.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.xiaolanhe.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/3 23:47
 */

@Slf4j
public class HessianRpcClient {
    public static void main(String[] args) throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();

        // 创建远端RPC服务代理对象
        String URL = "http://localhost:8989/Rpc-Hessian/userServiceRPC";

        UserService userService = (UserService) hessianProxyFactory.create(UserService.class, URL);
        boolean res = userService.login("xiaolanhe", "123456");
        log.debug("userServiceRet value is {}", res);
    }
}
