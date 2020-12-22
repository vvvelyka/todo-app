package com.kpi.project.todoapp.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

public class UserItem {

    private Long user_id;

    @NotEmpty(message = "Can`t be empty")
    @Size(min=2, max=50, message = "Firstname should be between 2 and 50 symbols")
    private String firstName;

    @NotEmpty(message = "Can`t be empty")
    @Size(min=2, max=50, message = "Lastname should be between 2 and 50 symbols")
    private String lastName;

    @NotEmpty(message = "Can`t be empty")
    @Size(min=6, max=320, message = "Email should be between 6 and 320 symbols")
    private String email;

    @NotEmpty(message = "Can`t be empty")
    @Size(min=8, max=50, message = "Password should be between 8 and 50 symbols")
    private String password;

    private List<Todo> todoList;

    public UserItem() {
    }

    public UserItem(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {

        return user_id;
    }

    public void setId(Long user_id) {

        this.user_id = user_id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", todoList=" + todoList +
                '}';
    }
}
