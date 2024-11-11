package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    List<User> getAllUsers();

    void removeUserById(Integer userId);

    User getUserById(Integer userId);

    void updateUser(User user);
}
