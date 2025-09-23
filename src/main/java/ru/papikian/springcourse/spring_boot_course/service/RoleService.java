package ru.papikian.springcourse.spring_boot_course.service;

import ru.papikian.springcourse.spring_boot_course.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleByRoleName(String roleName);
    void saveRole(Role role);
}