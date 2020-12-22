package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.mapper.UserMapper;

import com.kpi.project.todoapp.model.Todo;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


    private JdbcTemplate jdbcTemplate;
    private TodoDAO todoDAO;


    private final String SQL_FIND_USER = "select * from users where email = ?";
    private final String SQL_DELETE_PERSON = "delete from users where user_id = ?";
    private final String SQL_UPDATE_PERSON = "update users set first_name = ?, last_name = ?, email  = ? where user_id = ?";
    private final String SQL_INSERT_PERSON = "insert into users(first_name, last_name, email, password) values(?,?,?,?)";

    public UserDAOImpl() {
    }

    @Autowired
    public UserDAOImpl(DataSource dataSource, TodoDAOImpl todoDAO) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.todoDAO = todoDAO;
    }

    @Override
    public UserItem getUserByEmail(String email) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_FIND_USER, new Object[]{email}, new UserMapper());
        //????????????(SQL_FIND_PERSON, new UserMapper(), new Object[] { id });????????????????
    }

    @Override
    public UserItem validUser(String email, String password) {
        try {
            UserItem user = jdbcTemplate.queryForObject(SQL_FIND_USER, new Object[]{email}, new UserMapper());
            if (password.equals(user.getPassword())) {
                List<Todo> todoList = todoDAO.getAllTodos(user.getId());
                user.setTodoList(todoList);
                return user;
            } else {
                return null;
            }
        } catch (DataAccessException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean createUser(UserItem user) {
        return jdbcTemplate.update(SQL_INSERT_PERSON, user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword()) > 0;
    }

    @Override
    public boolean updateUser(UserItem user) {
        return jdbcTemplate.update(SQL_UPDATE_PERSON, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getId()) > 0;
    }

    @Override
    public boolean deleteUser(UserItem user) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, user.getId()) > 0;
    }
}
