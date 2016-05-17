package com.cinema.web;

import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();

        if (userService.check(login, password)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(3600);
            session.setAttribute("user", userService.findUserByLoginAndPassword(login, password));
            req.getSession().setAttribute("ifRegistered", "hidden");
            req.getSession().setAttribute("ifNotRegistered", "");
            if (userService.isAdmin(login, password)) {

                req.getSession().setAttribute("ifNotAdmin", "");
                resp.sendRedirect("/");
            } else {

                req.getSession().setAttribute("ifNotAdmin", "hidden");
                RequestDispatcher rs = req.getRequestDispatcher("/UserServlet");
                rs.forward(req, resp);
            }

        } else {
            out.println("<font color=\"red\">Username or Password incorrect</font>");
            RequestDispatcher rs = req.getRequestDispatcher("/mypage.jsp");
            rs.include(req, resp);
        }


    }
}
