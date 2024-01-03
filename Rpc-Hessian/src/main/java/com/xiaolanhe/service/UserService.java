package com.xiaolanhe.service;

import com.xiaolanhe.entity.User;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package com.xiaolanhe.service
 * @date 2024/1/3 23:40
 */
public interface UserService {
    public boolean login(String name, String password);

    public void register(User user);
}
