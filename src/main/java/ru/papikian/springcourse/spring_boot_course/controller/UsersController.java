package ru.papikian.springcourse.spring_boot_course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.papikian.springcourse.spring_boot_course.model.User;
import ru.papikian.springcourse.spring_boot_course.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/pages/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/pages/new";

    }
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/pages/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}