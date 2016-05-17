package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.UserDAO;
import com.cinema.dao.impl.UserDAOImpl;
import com.cinema.dto.UserDTO;
import com.cinema.model.User;
import com.cinema.service.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public void createUser(UserDTO userDTO) {
        UserDAO userDAO = new UserDAOImpl();
        User user = Transformer.userDtoToUser(userDTO);
        userDAO.createUser(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        UserDAO userDAO = new UserDAOImpl();
        List<UserDTO> userDTOs = Transformer.listUserToListUserDto(userDAO.findAllUsers());
        return userDTOs;
    }

    @Override
    public boolean deleteUserById(int id) {
        boolean result = false;
        try {
            UserDAO userDAO = new UserDAOImpl();
            userDAO.deleteUserById(id);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean check(String login, String password) {
        UserDAO userDAO = new UserDAOImpl();
        boolean result = userDAO.check(login, password);
        return result;
    }

    @Override
    public boolean isAdmin(String login, String password) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.isAdmin(login, password);
    }

    @Override
    public UserDTO findUserByLoginAndPassword(String login, String password) {
        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = Transformer.userToUserDto(userDAO.findUserByLoginAndPassword(login, password));
        return userDTO;
    }

    @Override
    public UserDTO findUserById(Integer idUser) {
        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = Transformer.userToUserDto(userDAO.findUserById(idUser));
        return userDTO;
    }

    @Override
    public boolean checkLoginToEquals(String login) {
        UserDAO userDAO = new UserDAOImpl();
        boolean result = userDAO.checkLoginToEquals(login);
        return result;
    }
}
