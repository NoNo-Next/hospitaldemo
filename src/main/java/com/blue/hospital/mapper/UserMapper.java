package com.blue.hospital.mapper;

import com.blue.hospital.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findUserById(int id);

    User findUserByName(String userName);
}
