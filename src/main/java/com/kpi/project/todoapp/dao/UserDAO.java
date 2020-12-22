package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.model.UserItem;

import java.util.List;

public interface UserDAO {


    UserItem getUserByEmail(String email);

    UserItem validUser(String email, String password);

    boolean deleteUser(UserItem user);

    boolean updateUser(UserItem user);

    boolean createUser(UserItem user);

}
