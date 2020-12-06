package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.mapper.TodoMapper;
import com.kpi.project.todoapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TodoDAOImpl implements TodoDAO{

    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_TODO = "select * from todos where todo_id = ?";
    private final String SQL_DELETE_TODO = "delete from todos where todo_id = ?";
    private final String SQL_UPDATE_TODO = "update todos set user_id = ?, title = ?, description  = ?, target_date = ?, is_done = ? where todo_id = ?";
    private final String SQL_GET_ALL = "select * from todos";
    private final String SQL_INSERT_TODO = "insert into todos(user_id, title, description, target_date, is_done) values(?,?,?,?)";

    @Autowired
    public TodoDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Todo> getAllTodos(Long user_id) {
        return jdbcTemplate.query(SQL_GET_ALL, new TodoMapper());
    }

    @Override
    public Todo getTodoById(Long todo_id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TODO, new Object[] { todo_id }, new TodoMapper());
        //????????????(SQL_FIND_PERSON, new UserMapper(), new Object[] { id });????????????????
    }

    @Override
    public boolean createTodo(Todo todo) {
        return jdbcTemplate.update(SQL_INSERT_TODO, todo.getUserId(), todo.getTitle(),
                todo.getDescription(), todo.getTargetDate(), todo.isDone()) > 0;
    }

    @Override
    public boolean updateTodo(Todo todo) {
        return jdbcTemplate.update(SQL_UPDATE_TODO, todo.getUserId(), todo.getTitle(),
                todo.getDescription(), todo.getTargetDate(), todo.isDone(),
                todo.getTodoId()) > 0;
    }

    @Override
    public boolean deleteTodo(Todo todo) {
        return jdbcTemplate.update(SQL_DELETE_TODO, todo.getTodoId()) > 0;
    }
}
