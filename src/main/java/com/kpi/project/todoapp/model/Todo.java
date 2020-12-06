package com.kpi.project.todoapp.model;

import java.util.Date;

public class Todo {

    private Long todo_id;
    private Long user_id;
    private String title;
    private String description;
    private Date targetDate; //LocalDate????
    private boolean is_done;

    public Todo() {
    }

    public Todo(Long user_id, String title, String description, Date targetDate) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
    }

    public Long getTodoId() {
        return todo_id;
    }

    public void setTodoId(Long todo_id) {
        this.todo_id = todo_id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return is_done;
    }

    public void setDone(boolean is_done) {
        this.is_done = is_done;
    }

}
