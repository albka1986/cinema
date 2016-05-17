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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@WebServlet("/")
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user") == null) {
            httpSession.setAttribute("ifRegistered", "");
            httpSession.setAttribute("ifNotRegistered", "hidden");
            httpSession.setAttribute("ifNotAdmin", "hidden");
        }


        MovieService movieService = new MovieServiceImpl();
        List<MovieDTO> movieDTOs = movieService.findAllMovies();
        req.getSession().setAttribute("movieDTOs", movieDTOs);

        SessionService sessionService = new SessionServiceImpl();
        List<SessionDTO> tempSessionDTOs = sessionService.findAllSessions();
        LocalDateTime timeNow = null;
        LocalDateTime sessionDate = null;
        List<SessionDTO> sessionDTOs = new LinkedList<>();

        for (SessionDTO tempSessionDTO : tempSessionDTOs) {
            timeNow = LocalDateTime.now();
            sessionDate = tempSessionDTO.getSessionDate();

            if (sessionDate.isAfter(timeNow)) {
                sessionDTOs.add(tempSessionDTO);
            }
        }


        Set<String> moviesTitles = new HashSet<>();
        for (int i = 0; i < sessionDTOs.size(); i++) {
            moviesTitles.add(sessionDTOs.get(i).getMovieDTO().getTitle());
        }

        req.getSession().setAttribute("moviesTitles", moviesTitles);


        RequestDispatcher rd = getServletContext().getRequestDispatcher("/mypage.jsp");
        rd.forward(req, resp);

    }


}
