package ru.papikian.springcourse.spring_boot_course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.repository.RoleRepository;
import ru.papikian.springcourse.spring_boot_course.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/create_new_user")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin/all_users";
    }

    @GetMapping("/")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers()); // Добавляем пользователей в модель
        model.addAttribute("user", new User()); // Добавляем пустого пользователя для формы создания
        model.addAttribute("roles", roleRepository.findAll());
        return "admin";
    }
    @GetMapping("/all_users")
    public String showAllUsers() {
        userService.getAllUsers();
        return "/admin";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id) {
        userService.getUserById(id);
        return "/admin";
    }
    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/all_users";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all_users";
    }
}
