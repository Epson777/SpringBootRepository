package ru.papikian.springcourse.spring_boot_course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.service.UserDetailService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/")
    public String userPage(Principal principal, Model model) {
        User user = userDetailService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
