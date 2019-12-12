package com.blue.hospital.service;

import com.blue.hospital.entity.User;

public interface UserService {
    User findUserById(int id);

    User findUserByName(String userName);
}
