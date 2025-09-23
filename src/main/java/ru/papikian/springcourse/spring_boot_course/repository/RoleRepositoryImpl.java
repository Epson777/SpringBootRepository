package ru.papikian.springcourse.spring_boot_course.repository;

import org.springframework.stereotype.Repository;
import ru.papikian.springcourse.spring_boot_course.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Optional<Role> getRoleByRoleName(String roleName) {
        try {
            Role role = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName).getSingleResult();
            return Optional.of(role);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}