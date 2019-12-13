package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.Job_user;
import org.springframework.stereotype.Repository;

@Repository
public interface Job_userMapper {
    public Job_user findJob_userByid(String id);
    public int addJob_user(Job_user user);
    public int updateJob_user(Job_user user);
}
