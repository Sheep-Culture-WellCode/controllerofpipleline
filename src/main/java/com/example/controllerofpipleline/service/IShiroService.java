package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.domin.User;

public interface IShiroService {
    User findByName(String u_name);
    User findById(int id);
}
