package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.papikian.springcourse.spring_boot_course.dao.UserDao;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.repository.RoleRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleRepository.findByRoleName("ROLE_USER")
                    .orElseGet(() -> {
                        Role newRole = new Role("ROLE_USER");
                        return roleRepository.save(newRole);
                    });
            user.setRoles(List.of(userRole)); // Используйте Set
        }
        userDao.createUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            User existingUser = userDao.getUserById(user.getId());
            user.setPassword(existingUser.getPassword());
        }
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
