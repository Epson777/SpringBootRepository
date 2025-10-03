package ru.papikian.springcourse.spring_boot_course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.service.RoleService;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AuthRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> registrationData) {
        String username = registrationData.get("username");
        String email = registrationData.get("email");
        String password = registrationData.get("password");
        String confirmPassword = registrationData.get("confirmPassword");

        if (userService.getUserByUsername(username) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists!"));
        }

        if (!password.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Passwords do not match!"));
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        Role userRole = roleService.getRoleByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role ROLE_USER not found"));
        user.setRoles(Collections.singletonList(userRole));

        userService.saveUser(user);

        return ResponseEntity.ok(Map.of("message", "Registration successful! Please login."));
    }
}