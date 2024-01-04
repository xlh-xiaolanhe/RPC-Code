package com.xiaolanhe;

import com.xiaolanhe.service.UserServiceImpl;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/4 23:46
 */
public class TestServer1 {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(9000);

        TFramedTransport.Factory tFramedTransport = new TFramedTransport.Factory();
        TCompactProtocol.Factory factory = new TCompactProtocol.Factory();

        UserService.Processor processor = new UserService.Processor(new UserServiceImpl());

        TThreadedSelectorServer.Args arg = new TThreadedSelectorServer.Args(tNonblockingServerSocket);
        arg.transportFactory(tFramedTransport);
        arg.protocolFactory(factory);
        arg.processor(processor);

        TServer tServer = new TThreadedSelectorServer(arg);
        tServer.serve();
    }
}
