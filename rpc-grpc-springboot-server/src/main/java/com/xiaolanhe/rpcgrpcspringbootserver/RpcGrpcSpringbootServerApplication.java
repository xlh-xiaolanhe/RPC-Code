package com.xiaolanhe.rpcgrpcspringbootserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class RpcGrpcSpringbootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcGrpcSpringbootServerApplication.class, args);
    }

}
