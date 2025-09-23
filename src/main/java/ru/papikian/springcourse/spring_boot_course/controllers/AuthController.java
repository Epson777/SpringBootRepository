package ru.papikian.springcourse.spring_boot_course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out successfully!");
        }
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {

        // Проверяем, существует ли пользователь
        if (userService.getUserByUsername(username) != null) {
            model.addAttribute("errorMessage", "Username already exists!");
            return "auth/registration";
        }

        // Создаем нового пользователя
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // Назначаем роль USER по умолчанию
        Role userRole = roleService.getRoleByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role ROLE_USER not found"));
        user.setRoles(Collections.singletonList(userRole));

        // Сохраняем пользователя
        userService.saveUser(user);

        model.addAttribute("successMessage", "Registration successful! Please login.");
        return "auth/login";
    }
}