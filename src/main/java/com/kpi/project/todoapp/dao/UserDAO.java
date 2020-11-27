package com.kpi.project.todoapp.dao;


import com.kpi.project.todoapp.model.UserItem;

import java.util.List;

public interface UserDAO {

    UserItem getUserById(Long id);

    List<UserItem> getAllUsers();

    boolean deleteUser(UserItem user);

    boolean updateUser(UserItem user);

    boolean createUser(UserItem user);

}
