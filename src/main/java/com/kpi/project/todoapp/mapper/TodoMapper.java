package com.kpi.project.todoapp.mapper;

import com.kpi.project.todoapp.model.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoMapper implements RowMapper<Todo> {

    public static final String BASE_SQL
            = "SELECT t.todo_id, t.user_id, t.title, t.description, t.target_date, t.status FROM todos t";

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {

        Todo todo = new Todo();
        todo.setTodoId(rs.getLong("todo_id"));
        todo.setUserId(rs.getLong("user_id"));
        todo.setTitle(rs.getString("title"));
        todo.setDescription(rs.getString("description"));
        todo.setTargetDate(rs.getDate("target_date"));
        todo.setDone(rs.getBoolean("status"));

        return todo;
    }
}
