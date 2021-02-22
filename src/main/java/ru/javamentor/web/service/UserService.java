package ru.javamentor.web.service;

import ru.javamentor.web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUser(long id);
    List<User> getAllUsers();
    void deleteUser(long id);
    void edit(User user);
}
