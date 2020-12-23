package com.kpi.project.todoapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Todo {

    private Long todo_id;
    private Long user_id;

    @NotNull
    @Size(min=3, max=50, message = "Title should be between 3 and 50 symbols")
    private String title;

    @Size(max=255, message = "Description can not be more than 255 symbols")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date targetDate;
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

    public boolean getIs_done() {
        return is_done;
    }

    public void setIs_done(boolean is_done) {
        this.is_done = is_done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todo_id=" + todo_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", is_done=" + is_done +
                '}';
    }
}
