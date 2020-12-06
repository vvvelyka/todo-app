package com.kpi.project.todoapp.model;


public class UserItem {

    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                ", username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
