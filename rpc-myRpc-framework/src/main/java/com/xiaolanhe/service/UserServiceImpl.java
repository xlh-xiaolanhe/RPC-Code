package com.xiaolanhe.service;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:48
 */
public class UserServiceImpl implements UserService{

    @Override
    public boolean login(String username, String password) {
        System.out.println("login method invoke " + username + " password is " + password);
        return false;
    }

}
