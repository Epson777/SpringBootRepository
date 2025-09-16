package ru.papikian.springcourse.spring_boot_course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.repository.RoleRepository;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;

        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/registration")
    public String registerPage(@ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        // Простая валидация
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            result.rejectValue("username", "error.user", "Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            result.rejectValue("password", "error.user", "Password is required");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "redirect:/auth/login?error";
        }

        try {
            Role userRole = roleRepository.findByRoleName("ROLE_USER")
                    .orElseGet(() -> {
                        Role newRole = new Role("ROLE_USER");
                        return roleRepository.save(newRole);
                    });
            user.setRoles(List.of(userRole));

            userService.createUser(user);
            model.addAttribute("success", "Registration successful! Please login.");
            return "redirect:/auth/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            model.addAttribute("user", user);
            return "login";
        }
    }
}
