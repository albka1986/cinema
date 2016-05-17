package com.cinema.web;

import com.cinema.service.api.SessionService;
import com.cinema.service.impl.SessionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteSessionServlet")
public class DeleteSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SessionService sessionService = new SessionServiceImpl();
            Integer idSession = Integer.valueOf(req.getParameter("idSessionDelete"));
            sessionService.deleteSessionById(idSession);

        } finally {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin");
            rd.forward(req, resp);
        }


    }
}
