package com.xiaolanhe.consul;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.health.ServiceHealth;

import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/29 23:01
 */
public class TestClient {
    public static void main(String[] args) {
        Consul consulConnection = Consul.builder().build();
        HealthClient healthClient = consulConnection.healthClient();

        ConsulResponse<List<ServiceHealth>> healthyServiceInstances = healthClient.getHealthyServiceInstances("grpc-server");

        List<ServiceHealth> response = healthyServiceInstances.getResponse();
        response.stream().forEach(e -> {
            System.out.println(e.getService().getAddress() + ":" + e.getService().getPort());
        });
    }
}
