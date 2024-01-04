package com.xiaolanhe;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/4 23:47
 */
public class TestCilent1 {
    public static void main(String[] args) throws TException {
        //完成  与服务端 网络连接的连接
        TTransport tTransport = new TSocket("localhost", 9000);
        TFramedTransport tFramedTransport = new TFramedTransport(tTransport);
        tTransport.open();

        //创建协议
        TCompactProtocol tCompactProtocol = new TCompactProtocol(tFramedTransport);

        //创建代理  stub 存根 桩
        UserService.Client userService = new UserService.Client(tCompactProtocol);

        User user = userService.queryUserByNameAndPassword("xiaojr", "9090");
        System.out.println("user = " + user);
    }
}
