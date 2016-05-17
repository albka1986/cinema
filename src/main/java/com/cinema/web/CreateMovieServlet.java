package com.cinema.web;

import com.cinema.dto.MovieDTO;
import com.cinema.service.api.MovieService;
import com.cinema.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateMovieServlet")
public class CreateMovieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            Integer duration = Integer.parseInt(req.getParameter("duration"));

            MovieService movieService = new MovieServiceImpl();
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle(title);
            movieDTO.setDescription(description);
            movieDTO.setDuration(duration);
            movieService.createMovie(movieDTO);


            resp.sendRedirect("/Admin");
        } catch (Exception e) {
            resp.sendRedirect("/Admin");
        }

    }
}
