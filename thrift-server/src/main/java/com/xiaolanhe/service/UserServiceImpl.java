package com.xiaolanhe.service;

import com.xiaolanhe.User;
import com.xiaolanhe.UserService;
import org.apache.thrift.TException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/4 23:40
 */
public class UserServiceImpl implements UserService.Iface {
    @Override
    public User queryUserByNameAndPassword(String name, String password) throws TException {
        System.out.println("UserServiceImpl.queryUserByNameAndPassword name is " + name + " password is " + password);
        return new User("xiaojr", "88888");
    }

    @Override
    public void save(User user) throws TException {
        System.out.println("save user is " + user.getName() + " password is " + user.getPassword());
    }
}
