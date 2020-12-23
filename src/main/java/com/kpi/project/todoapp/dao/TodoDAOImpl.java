package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.mapper.TodoMapper;
import com.kpi.project.todoapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public class TodoDAOImpl implements TodoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_TODO = "select * from todos where todo_id = ?";
    private final String SQL_DELETE_TODO = "delete from todos where todo_id = ?";
    private final String SQL_UPDATE_TODO = "update todos set title = ?, description  = ?, target_date = ?, status = ? where todo_id = ?";
    private final String SQL_GET_ALL = "select * from todos where user_id = ?";
    private final String SQL_INSERT_TODO = "insert into todos(user_id, title, description, target_date) values(?,?,?,?)";

    public TodoDAOImpl() {
    }

    @Override
    public List<Todo> getAllTodos(Long user_id) {

        List<Todo> todoList = new ArrayList<>() ;

        List<Map<String, Object>> todos = jdbcTemplate.queryForList(SQL_GET_ALL, user_id);

        todos.forEach( rowMap -> {
            Todo todo = new Todo();
            todo.setTodoId((Long) rowMap.get("todo_id"));
            todo.setUserId(user_id);
            todo.setTitle((String) rowMap.get("title"));
            todo.setDescription((String) rowMap.get("description"));
            todo.setTargetDate((Date) rowMap.get("target_date"));
            todo.setIs_done((boolean) rowMap.get("status"));
            todoList.add(todo);
        });

//        System.out.println(todoList.size());

        return todoList;
    }

    @Override
    public Todo getTodoById(Long todo_id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TODO, new Object[] { todo_id }, new TodoMapper());
    }

    @Override
    public boolean createTodo(Todo todo) {
        return jdbcTemplate.update(SQL_INSERT_TODO, todo.getUserId(), todo.getTitle(),
                todo.getDescription(), todo.getTargetDate()) > 0;
    }

    @Override
    public boolean updateTodo(Todo todo) {
        return jdbcTemplate.update(SQL_UPDATE_TODO, todo.getTitle(),
                todo.getDescription(), todo.getTargetDate(), todo.getIs_done(),
                todo.getTodoId()) > 0;
    }

    @Override
    public boolean deleteTodo(Todo todo) {
        return jdbcTemplate.update(SQL_DELETE_TODO, todo.getTodoId()) > 0;
    }
}
