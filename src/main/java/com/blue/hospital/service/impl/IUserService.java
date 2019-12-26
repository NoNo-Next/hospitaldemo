package com.blue.hospital.service.impl;

import com.blue.hospital.entity.User;
import com.blue.hospital.mapper.UserMapper;
import com.blue.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserService implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }


}
