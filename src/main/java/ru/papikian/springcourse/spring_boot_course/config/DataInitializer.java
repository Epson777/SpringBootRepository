package ru.papikian.springcourse.spring_boot_course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.service.RoleService;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class DataInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        // Создаем роли, если их нет
        if (roleService.getRoleByRoleName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role("ROLE_ADMIN");
            entityManager.persist(adminRole);
        }

        if (roleService.getRoleByRoleName("ROLE_USER").isEmpty()) {
            Role userRole = new Role("ROLE_USER");
            entityManager.persist(userRole);
        }

        // Создаем администратора, если его нет
        if (userService.getUserByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@mail.com");

            Role adminRole = roleService.getRoleByRoleName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));
            admin.setRoles(Collections.singletonList(adminRole));

            userService.createUser(admin);
        }

        // Создаем тестового пользователя
        if (userService.getUserByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@mail.com");

            Role userRole = roleService.getRoleByRoleName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
            user.setRoles(Collections.singletonList(userRole));

            userService.createUser(user);
        }
    }
}