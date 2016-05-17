package com.cinema.service.api;

import com.cinema.dto.UserDTO;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();

    boolean deleteUserById(int id);

    boolean check(String login, String password);

    boolean isAdmin(String login, String password);

    UserDTO findUserByLoginAndPassword(String login, String password);

    UserDTO findUserById(Integer idUser);

    boolean checkLoginToEquals(String login);
}
