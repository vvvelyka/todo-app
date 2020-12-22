package com.kpi.project.todoapp;

import com.kpi.project.todoapp.dao.UserDAO;
import com.kpi.project.todoapp.dao.UserDAOImpl;
import com.kpi.project.todoapp.model.UserItem;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;
    UserItem user;

    public UserDAOTest() {
        this.userDAO = new UserDAOImpl();;
        this.user = new UserItem("Tom", "Smith", "tom.smith@gmail.com", "ForgetMeNot111");
    }

    @Test
    @Order(1)
    void register(){
        boolean res = userDAO.createUser(this.user);
        assertTrue("User is registered", res);
    }

    @Test
    @Order(2)
    void login(){
        UserItem user = userDAO.validUser(this.user.getEmail(), this.user.getPassword());
        assertNotNull("User is not null", user);
    }

    @Test
    @Order(3)
    void delete(){
        boolean res = userDAO.deleteUser(this.user);
        assertTrue("User is deleted", res);
    }

}
