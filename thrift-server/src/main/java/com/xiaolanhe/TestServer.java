package com.xiaolanhe;

import com.xiaolanhe.service.UserServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/4 23:37
 */
public class TestServer {
    public static void main(String[] args) throws TTransportException {
        TServerTransport tServerTransport = new TServerSocket(9000);

        TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

        // 把对应的功能书写发布
        UserService.Processor processor = new UserService.Processor(new UserServiceImpl());

        TServer.Args arg = new TServer.Args(tServerTransport);
        arg.processor(processor);
        arg.protocolFactory(factory);

        // TServer 发布服务
        TSimpleServer server = new TSimpleServer(arg);
        server.serve();
    }
}
