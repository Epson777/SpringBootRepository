package ru.papikian.springcourse.spring_boot_course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        List<User> users = userService.getAllUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView getUser(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("user-details");
        User user = userService.getUserById(id).orElse(null);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newUserForm(@ModelAttribute("user") User user) {
        return new ModelAndView("new-user");
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/edit")
    public ModelAndView editUserForm(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit-user");
        User user = userService.getUserById(id).orElse(null);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("user") User user,
                                   @RequestParam("id") int id) {
        user.setId(id);
        userService.updateUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin");
    }
}