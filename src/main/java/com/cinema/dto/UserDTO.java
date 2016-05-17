package com.cinema.dto;

import com.cinema.model.Role;

import java.time.LocalDate;
import java.util.Date;

public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private LocalDate  birthday;
    private Role role;
    private String email;

    public UserDTO(){
        this.role = Role.USER;
    }

    public UserDTO(Integer id, String name, String surname, String login, String password, LocalDate birthday, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.role = Role.USER;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate  getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate  birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}'+
                "\n";
    }
}
