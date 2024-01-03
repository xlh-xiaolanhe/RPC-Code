package com.xiaolanhe.service;

import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/3 23:45
 */

@Slf4j
public class OrderServiceImpl implements OrderService{
    @Override
    public void showOrder() {
      log.debug("showOrder method has been invoked");
    }
}
