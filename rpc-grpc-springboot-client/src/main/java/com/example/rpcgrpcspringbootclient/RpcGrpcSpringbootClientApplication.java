package com.example.rpcgrpcspringbootclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class RpcGrpcSpringbootClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcGrpcSpringbootClientApplication.class, args);
    }

}
