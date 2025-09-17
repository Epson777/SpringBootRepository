package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserByUserId(id);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.getUserByUserUsername(username);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
