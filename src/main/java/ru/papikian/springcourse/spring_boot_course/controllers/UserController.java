package ru.papikian.springcourse.spring_boot_course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userDetailService) {
        this.userService = userDetailService;
    }

    @GetMapping()
    public ModelAndView userPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("user");
        User user = userService.getUserByUsername(principal.getName());
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}