package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.papikian.springcourse.spring_boot_course.repository.RoleRepository;

@Service
public class RoleDetailsService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleDetailsService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
