package com.kpi.project.todoapp.controller;

import com.kpi.project.todoapp.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

public class MainController {

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        return "signup";
    }

    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
    public String todolist(Model model) {

        return "todolist";
    }

    @RequestMapping(value = "/addtodo", method = RequestMethod.GET)
    public String addtodo(Model model) {

        return "addtodo";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginUser);
        model.addAttribute("userInfo", userInfo);

        return "admin";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}
