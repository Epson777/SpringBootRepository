package ru.papikian.springcourse.spring_boot_course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.papikian.springcourse.spring_boot_course.dao.UserDao;
import ru.papikian.springcourse.spring_boot_course.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
