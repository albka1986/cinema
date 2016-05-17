package com.cinema.web;

import com.cinema.dto.MovieDTO;
import com.cinema.dto.SessionDTO;
import com.cinema.service.api.MovieService;
import com.cinema.service.api.SessionService;
import com.cinema.service.impl.MovieServiceImpl;
import com.cinema.service.impl.SessionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MovieService movieService = new MovieServiceImpl();
        MovieDTO movie = movieService.findMovieByTitle(request.getParameter("movieTitle"));
        request.getSession().setAttribute("movie", movie);

        SessionService sessionService = new SessionServiceImpl();
        List<SessionDTO> sessionDTOs = sessionService.findSessionsByMovieId(movie.getId());
        request.getSession().setAttribute("sessionDTOs", sessionDTOs);


        RequestDispatcher rd = getServletContext().getRequestDispatcher("/movie.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
