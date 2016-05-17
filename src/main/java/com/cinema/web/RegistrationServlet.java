package com.cinema.web;

import com.cinema.dto.UserDTO;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        String email = req.getParameter("email");

        UserService userService = new UserServiceImpl();
        if (!userService.checkLoginToEquals(login)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(name);
            userDTO.setSurname(surname);
            userDTO.setLogin(login);
            userDTO.setPassword(password);
            userDTO.setBirthday(birthday);
            userDTO.setEmail(email);
            userService.createUser(userDTO);

            req.getRequestDispatcher("mypage.jsp").forward(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<font color=\"red\">Login is occupied!</font>");
            RequestDispatcher rs = req.getRequestDispatcher("/registration.jsp");
            rs.include(req, resp);
        }

    }

}
