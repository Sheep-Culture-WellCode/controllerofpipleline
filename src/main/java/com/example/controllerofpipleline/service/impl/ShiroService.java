package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.domin.User;
import com.example.controllerofpipleline.mapper.UserMapper;
import com.example.controllerofpipleline.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroService implements IShiroService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findByName(String u_name) {
        return userMapper.findByName(u_name);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }
}
