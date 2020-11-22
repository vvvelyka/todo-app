package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserDao {

    public List<Map<String, User>> getAll();
    Optional<User> getById(int id) throws Exception;
    User findUserByEmail(String email);
    boolean add(User user) throws Exception;
    boolean update(User user) throws Exception;
    boolean delete(User user) throws Exception;

}
