package ru.papikian.springcourse.spring_boot_course.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.models.User;
import ru.papikian.springcourse.spring_boot_course.repository.RoleRepository;
import ru.papikian.springcourse.spring_boot_course.repository.UserRepository;
import ru.papikian.springcourse.spring_boot_course.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    @Transactional
    public void init() {
        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(List.of(userRole));
            userRepository.save(user);
        }
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@example.com");
            admin.setRoles(List.of(adminRole, userRole));
            userRepository.save(admin);
        }
    }
}
