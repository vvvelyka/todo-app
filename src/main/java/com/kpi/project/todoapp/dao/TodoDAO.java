package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.model.Todo;

import java.util.List;

public interface TodoDAO {

    List<Todo> getAllTodos(Long user_id);

    Todo getTodoById( Long todo_id);

    boolean createTodo(Todo todo);

    boolean updateTodo(Todo todo);

    boolean deleteTodo(Todo todo);

}
