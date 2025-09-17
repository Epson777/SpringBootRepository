package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.papikian.springcourse.spring_boot_course.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteUser(Integer id);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
