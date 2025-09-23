package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.papikian.springcourse.spring_boot_course.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    void updateUser(User user);
    void deleteUser(Integer id);
    User getUserByUsername(String username);
}
