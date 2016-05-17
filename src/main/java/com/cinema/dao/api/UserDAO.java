package com.cinema.dao.api;

import com.cinema.model.User;

import java.util.List;

public interface UserDAO {

    void createUser(User user);

    List<User> findAllUsers();

    void deleteUserById(int id);

    boolean check(String login, String password);

    boolean isAdmin(String login, String password);

    User findUserByLoginAndPassword(String login, String password);

    User findUserById(Integer idUser);

    boolean checkLoginToEquals(String login);



}
