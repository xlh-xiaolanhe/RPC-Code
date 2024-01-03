package com.xiaolanhe.service;/**
 * @Package com.xiaolanhe.service
 * @author xiaolanhe
 * @date 2024/1/3 23:42
 * @version V1.0
 */

import com.xiaolanhe.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/3 23:42
 */
@Slf4j
public class UserServiceImpl implements UserService{

    @Override
    public boolean login(String name, String password) {
        log.debug("login method invoke name {} password {}", name, password);
        return false;
    }

    @Override
    public void register(User user) {
        log.debug("register method invoke {}", user);
    }
}
