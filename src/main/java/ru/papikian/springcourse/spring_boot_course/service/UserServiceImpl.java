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
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
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
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
    }
}
