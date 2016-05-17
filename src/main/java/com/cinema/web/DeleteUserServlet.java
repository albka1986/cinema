package com.cinema.web;

import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserServiceImpl();
            Integer idUser = Integer.parseInt(req.getParameter("idUserDelete"));
            userService.deleteUserById(idUser);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin");
            rd.forward(req, resp);
        } finally {

        }

    }

}
