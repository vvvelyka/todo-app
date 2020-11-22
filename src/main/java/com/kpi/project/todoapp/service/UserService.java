package com.kpi.project.todoapp.service;

import com.kpi.project.todoapp.dao.UserDao;

public interface UserService {

    UserDao createUser(UserDao userDto);
    UserDao login(UserDao userDto);
    UserDao findUserByEmail(String email);
    UserDao updateProfile(UserDao userDto);
    UserDao changePassword(UserDao userDto, String newPassword);
}
