package com.kpi.project.todoapp.controller;

//import com.kpi.project.todoapp.utils.WebUtils;
//import org.springframework.security.core.Authentication;
import com.kpi.project.todoapp.dao.UserDAO;
import com.kpi.project.todoapp.dao.UserDAOImpl;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {


    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    //@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    @GetMapping("/")
    public String startPage() {
        return "/login";
    }


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

        UserItem user = new UserItem();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute("user") UserItem user) {

        if(userDAO.createUser(user)) {
            return "success";
        }
        return "error";
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
