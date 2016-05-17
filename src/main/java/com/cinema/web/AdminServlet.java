package com.cinema.web;

import com.cinema.dto.MovieDTO;
import com.cinema.dto.SessionDTO;
import com.cinema.dto.UserDTO;
import com.cinema.model.Role;
import com.cinema.service.api.MovieService;
import com.cinema.service.api.SessionService;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.MovieServiceImpl;
import com.cinema.service.impl.SessionServiceImpl;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserServiceImpl();

        try {
            HttpSession httpSession = req.getSession();
            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
            if (userDTO.getRole() != Role.ADMIN) {
                resp.sendRedirect("/");
            }
        } catch (IOException e) {
            resp.sendRedirect("/");
        }

        List<UserDTO> userDTOs = userService.findAllUsers();
        req.getSession().setAttribute("userDTOs", userDTOs);

        MovieService movieService = new MovieServiceImpl();
        List<MovieDTO> movieDTOs = movieService.findAllMovies();
        req.getSession().setAttribute("movieDTOs", movieDTOs);

        SessionService sessionService = new SessionServiceImpl();
        List<SessionDTO> sessionDTOs = sessionService.findAllSessions();
        req.getSession().setAttribute("sessionDTOs", sessionDTOs);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        MovieService movieService = new MovieServiceImpl();
        List<MovieDTO> movieDTOs = movieService.findAllMovies();
        req.getSession().setAttribute("movieDTOs", movieDTOs);

        UserService userService = new UserServiceImpl();
        List<UserDTO> userDTOs = userService.findAllUsers();
        req.getSession().setAttribute("userDTOs", userDTOs);

        SessionService sessionService = new SessionServiceImpl();
        List<SessionDTO> sessionDTOs = sessionService.findAllSessions();
        req.getSession().setAttribute("sessionDTOs", sessionDTOs);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin.jsp");
        rd.forward(req, resp);
    }
}
