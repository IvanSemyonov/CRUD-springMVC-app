package ru.javamentor.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.web.dao.UserDAO;
import ru.javamentor.web.model.Role;
import ru.javamentor.web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        User userFromDB = userDAO.getUser(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(new Role(1L, "ROLE_USER"));
        userDAO.addUser(user);
        return true;
    }

    @Transactional
    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Override
    public User getUser(String name) {
        return userDAO.getUser(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }
}
