package com.kpi.project.todoapp.controller;

import com.kpi.project.todoapp.dao.TodoDAO;
import com.kpi.project.todoapp.dao.UserDAO;
import com.kpi.project.todoapp.dao.UserDAOImpl;
import com.kpi.project.todoapp.model.Todo;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class MainController {


    private UserDAO userDAO;
    private UserItem user;
    private Todo currentTodo;
    @Autowired
    private TodoDAO todoDAO;


    @Autowired
    public MainController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
        user = new UserItem();
    }

    @GetMapping("/")
    public String startPage(Model model) {
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserItem user, Errors errors, Model model) throws Exception {

        this.user = userDAO.validUser(user.getEmail(), user.getPassword());
        if ( this.user != null) {
            model.addAttribute("todoList", this.user.getTodoList());
            return "todolist";
        } else {
            model.addAttribute("message", "Wrong email or password");
            return "login";
        }

    }

    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/save_user")
    public String saveUser(@Valid @ModelAttribute("user") UserItem user, Errors errors) {

        if (errors.getErrorCount() > 0) {
            return "signup";
        } else if (userDAO.createUser(user)) {
            return "login";
        } else {
            return "error";
        }
    }

    @GetMapping("/add_todo")
    public String addTodo(Model model) {

        model.addAttribute("todo", new Todo());
        return "todo-form";
    }

    @PostMapping("/save_todo")
    public String saveTodo(@Valid @ModelAttribute("todo") Todo todo, Errors errors, Model model) {
        todo.setUserId(user.getId());
        System.out.println(todo.toString());
        if (errors.getErrorCount() > 0) {
            return "todo-form";
        } else if (todoDAO.createTodo(todo)) {
            user.setTodoList(todoDAO.getAllTodos(user.getId()));
            model.addAttribute("todoList", user.getTodoList());
            return "todolist";
        } else {
            return "error";
        }
    }

    @GetMapping("/edit_todo/{id}")
    public String editTodo(@PathVariable("id") Long id, Model model) {
        currentTodo = todoDAO.getTodoById(id);
        model.addAttribute("todo", currentTodo);
        return "todo-edit";
    }

    @PostMapping("/update_todo")
    public String updateTodo(@Valid @ModelAttribute("todo") Todo todo, Errors errors, Model model) {
//        todo.setUserId(user.getId());
        todo.setTodoId(currentTodo.getTodoId());
//        System.out.println(todo.getTodoId());
        System.out.println(todo.toString());
        if (errors.getErrorCount() > 0) {
            return "todo-form";
        } else if (todoDAO.updateTodo(todo)) {
            user.setTodoList(todoDAO.getAllTodos(user.getId()));
            model.addAttribute("todoList", user.getTodoList());
            return "todolist";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete_todo/{id}")
    public String deleteTodo(@PathVariable("id") Long id, Model model) {
        if(todoDAO.deleteTodo(todoDAO.getTodoById(id))) {
            user.setTodoList(todoDAO.getAllTodos(user.getId()));
            model.addAttribute("todoList", user.getTodoList());
            return "todolist";
        } else {
            return "error";
        }

    }

    @GetMapping("/user_info")
    public String userInfo(Model model) {
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/update_user")
    public String updateUser(@Valid @ModelAttribute("user") UserItem user, Errors errors, Model model) {

        user.setId(this.user.getId());
        if (errors.getErrorCount() > 0) {
            return "user-info";
        } else if (userDAO.updateUser(user)) {
            this.user = user;
            user.setTodoList(todoDAO.getAllTodos(user.getId()));
            model.addAttribute("todoList", user.getTodoList());
            return "todolist";
        } else {
            return "error";
        }

    }

    @GetMapping("/delete_user")
    public String deleteUser (Model model) {
        if (userDAO.deleteUser(user)) {
            model.addAttribute("user", user);
            return "login";
        } else {
            return "error";
        }
    }

}
