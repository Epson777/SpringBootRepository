package ru.papikian.springcourse.spring_boot_course.repository;

import org.springframework.stereotype.Repository;
import ru.papikian.springcourse.spring_boot_course.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}