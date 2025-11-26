package uth.edu.TrinhHoangChuong.controller;

import uth.edu.TrinhHoangChuong.model.User;
import uth.edu.TrinhHoangChuong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "home";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String handleUserAction(@RequestParam String action,
                                   @RequestParam(required = false) String id,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName,
                                   @RequestParam(required = false) String mark,
                                   Model model) {
        
        try {
            if ("add".equals(action)) {
                User user = new User(id, firstName, lastName, mark);
                userService.save(user);
            } else if ("update".equals(action)) {
                User user = userService.findById(id);
                if (user != null) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setMark(mark);
                    userService.update(user);
                }
            } else if ("delete".equals(action)) {
                userService.delete(id);
            }
        } catch (Exception e) {
            System.err.println("Error handling user action: " + e.getMessage());
            e.printStackTrace();
        }
        
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "home";
    }
}

