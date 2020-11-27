package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.mapper.UserMapper;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    JdbcTemplate jdbcTemplate;

    final String SQL_FIND_PERSON = "select * from users where id = ?";
    final String SQL_DELETE_PERSON = "delete from users where id = ?";
    final String SQL_UPDATE_PERSON = "update users set first_name = ?, last_name = ?, email  = ? where id = ?";
    final String SQL_GET_ALL = "select * from users";
    final String SQL_INSERT_PERSON = "insert into users(id, first_name, last_name, email, password) values(?,?,?,?)";

    @Autowired
    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserItem getUserById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new UserMapper(), new Object[] { id });
    }

    @Override
    public List<UserItem> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
    }

    @Override
    public boolean deleteUser(UserItem user) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, user.getId()) > 0;
    }

    @Override
    public boolean updateUser(UserItem user) {
        return jdbcTemplate.update(SQL_UPDATE_PERSON, user.getFirstName(), user.getLastName(), user.getUserName(),
                user.getId()) > 0;
    }

    @Override
    public boolean createUser(UserItem user) {
        return jdbcTemplate.update(SQL_INSERT_PERSON, user.getId(), user.getFirstName(), user.getLastName(),
                user.getUserName(), user.getPassword()) > 0;
    }
}
