package ru.javamentor.web.dao;

import org.springframework.stereotype.Repository;
import ru.javamentor.web.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(long id);
    List<User> getAllUsers();
    void deleteUser(long id);
    void edit(User user);
}
