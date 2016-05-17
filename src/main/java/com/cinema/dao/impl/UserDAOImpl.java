package com.cinema.dao.impl;

import com.cinema.dao.api.UserDAO;
import com.cinema.datasource.DataSource;
import com.cinema.model.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public void createUser(User user) {
        DataSource.createUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = DataSource.findAllUsers();
        return users;
    }

    @Override
    public void deleteUserById(int id) {
        DataSource.deleteUserById(id);

    }

    @Override
    public boolean check(String login, String password) {
        return DataSource.checkLoginPasswordToValid(login, password);
    }

    @Override
    public boolean isAdmin(String login, String password) {
        return DataSource.isAdmin(login, password);
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = DataSource.findUserByLoginAndPassword(login, password);
        return user;
    }

    @Override
    public User findUserById(Integer idUser) {
        User user = DataSource.findUserById(idUser);
        return user;
    }

    @Override
    public boolean checkLoginToEquals(String login) {
        boolean result = DataSource.checkLoginToEquals(login);
        return result;
    }
}
