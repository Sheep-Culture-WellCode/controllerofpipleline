package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    User findByName(String u_name);
    User findById(int id);
}
