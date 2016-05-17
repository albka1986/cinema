package com.cinema.web;

import com.cinema.dto.MovieDTO;
import com.cinema.dto.SessionDTO;
import com.cinema.service.api.SessionService;
import com.cinema.service.impl.SessionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/CreateSessionServlet")
public class CreateSessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(Integer.parseInt(req.getParameter("id_movie")));

            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setMovieDTO(movieDTO);
            sessionDTO.setSessionDate(LocalDateTime.parse(req.getParameter("sessionDate")));

            SessionService sessionService = new SessionServiceImpl();
            sessionService.createSession(sessionDTO);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin");
            rd.forward(req, resp);
        } catch (Exception e) {

        }
    }
}
