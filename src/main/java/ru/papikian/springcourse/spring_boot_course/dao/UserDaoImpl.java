package ru.papikian.springcourse.spring_boot_course.dao;

import org.springframework.stereotype.Repository;
import ru.papikian.springcourse.spring_boot_course.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
       return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
