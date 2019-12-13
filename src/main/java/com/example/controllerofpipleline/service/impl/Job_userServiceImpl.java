package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.domin.Job_user;
import com.example.controllerofpipleline.mapper.Job_userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Job_userServiceImpl implements com.example.controllerofpipleline.service.Job_userService {
    @Autowired
    Job_userMapper userMapper;
    @Override
    public Job_user findJob_userByid(String id) {
        return userMapper.findJob_userByid(id);
    }

    @Override
    public int addJob_user(Job_user user) {
        return userMapper.addJob_user(user);
    }

    @Override
    public int updateJob_user(Job_user user) {
        return userMapper.updateJob_user(user);
    }
}
