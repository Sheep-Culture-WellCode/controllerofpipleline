package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.domin.Job_user;

public interface Job_userService {
    public Job_user findJob_userByid(String id);
    public int addJob_user(Job_user user);
    public int updateJob_user(Job_user user);
}
