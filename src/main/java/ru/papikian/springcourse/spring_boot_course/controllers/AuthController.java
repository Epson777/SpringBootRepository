package ru.papikian.springcourse.spring_boot_course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.service.RoleService;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("auth/login");

        if (error != null) {
            modelAndView.addObject("errorMessage", "Invalid username or password!");
        }
        if (logout != null) {
            modelAndView.addObject("logoutMessage", "You have been logged out successfully!");
        }
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("auth/registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registerUser(@RequestParam("username") String username,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();

        if (userService.getUserByUsername(username) != null) {
            modelAndView.setViewName("auth/registration");
            modelAndView.addObject("errorMessage", "Username already exists!");
            return modelAndView;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        Role userRole = roleService.getRoleByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role ROLE_USER not found"));
        user.setRoles(Collections.singletonList(userRole));

        userService.saveUser(user);

        modelAndView.setViewName("auth/login");
        modelAndView.addObject("successMessage", "Registration successful! Please login.");
        return modelAndView;
    }
}