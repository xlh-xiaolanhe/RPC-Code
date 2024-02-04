package com.xiaolanhe.discover;

import java.util.List;
import java.util.Random;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/4 21:56
 */
public class RandomLoadBalance implements LoadBalance{

    private Random random = new Random();

    @Override
    public String select(List<String> serviceAddresses) {
        int index = random.nextInt(serviceAddresses.size());
        return serviceAddresses.get(index);
    }
}
