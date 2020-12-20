package com.kpi.project.todoapp.controller;

//import com.kpi.project.todoapp.utils.WebUtils;
//import org.springframework.security.core.Authentication;

import com.kpi.project.todoapp.dao.TodoDAO;
import com.kpi.project.todoapp.dao.UserDAO;
import com.kpi.project.todoapp.dao.UserDAOImpl;
import com.kpi.project.todoapp.model.Todo;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {


    private UserDAO userDAO;
    private UserItem user;
    @Autowired
    private TodoDAO todoDAO;



    @Autowired
    public UserController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/")
    public String startPage(Model model) {
        user = new UserItem();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserItem user, Errors errors, Model model) throws Exception {

//        List<ObjectError> errorList = errors.getAllErrors();
//        for(ObjectError itr : errorList){
//            System.out.println(itr.toString());
//        }

        //System.out.println(bindingResult.hasErrors());
//        if (errors.getErrorCount() > 0) {
//            return "login";
//        } else
        this.user = userDAO.validUser(user.getEmail(), user.getPassword());
        if ( this.user != null) {

//            for(int i=0;i<user.getTodoList().size();i++){
//                System.out.println(user.getTodoList().get(i));
//            }
            model.addAttribute("todoList", this.user.getTodoList());
            //model.addAttribute("message", "Wrong email or password");
            return "todolist";
        } else {
            model.addAttribute("message", "Wrong email or password");
            return "login";
        }

    }

//    @PostMapping("/login")
//    public String auth() {
//        return "success";
//    }


//    @GetMapping("/login?error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }
//    @PostMapping("/")
//    public String login(@Valid UserItem userItem, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "/signup";
//        }
//
//        return "/success";
//    }

//    @RequestMapping({"/", "/login"})
////    @PostMapping(value = "/success")
//    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
//
//        return "/success";
//    }

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
    System.out.println(user.getId());
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
            return "todolist";
        } else {
            return "error";
        }
    }
//
//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public String signup() {
//        return "signup";
//    }
//
//    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
//    public String todolist() {
//        return "todolist";
//    }
//
//    @RequestMapping(value = "/addtodo", method = RequestMethod.GET)
//    public String addtodo() {
//        return "addtodo";
//    }
//
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(Model model, Principal principal) {
//
//        User loginUser = (User) ((Authentication) principal).getPrincipal();
//
//        String userInfo = WebUtils.toString(loginUser);
//        model.addAttribute("userInfo", userInfo);
//
//        return "admin";
//    }
//
//    @RequestMapping(value = "/403", method = RequestMethod.GET)
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            User loginUser = (User) ((Authentication) principal).getPrincipal();
//
//            String userInfo = WebUtils.toString(loginUser);
//
//            model.addAttribute("userInfo", userInfo);
//
//            String message = "Hi " + principal.getName() //
//                    + "<br> You do not have permission to access this page!";
//            model.addAttribute("message", message);
//
//        }
//
//        return "403Page";
//    }

}
