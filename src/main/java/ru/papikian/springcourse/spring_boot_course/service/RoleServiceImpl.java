package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.papikian.springcourse.spring_boot_course.models.Role;
import ru.papikian.springcourse.spring_boot_course.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Optional<Role> getRoleByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.saveRole(role);
    }
}