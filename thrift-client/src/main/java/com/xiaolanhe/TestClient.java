package com.xiaolanhe;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/4 23:31
 */
public class TestClient {
    public static void main(String[] args) throws TException {
        //完成与 服务端 网络连接的连接
        TTransport transport = new TSocket("localhost", 9000);
        transport.open();

        // 创建协议
        TBinaryProtocol protocol = new TBinaryProtocol(transport);

        //创建代理  stub 存根 桩
        UserService.Client userService = new UserService.Client(protocol);


        User user = userService.queryUserByNameAndPassword("xiaolanhe", "123456");
        System.out.println(user);
    }
}
