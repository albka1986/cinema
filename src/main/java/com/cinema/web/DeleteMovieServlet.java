package com.cinema.web;

import com.cinema.service.api.MovieService;
import com.cinema.service.impl.MovieServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteMovieServlet")
public class DeleteMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer idMovie = Integer.valueOf(req.getParameter("idMovieDelete"));
            MovieService movieService = new MovieServiceImpl();
            movieService.deleteMovieById(idMovie);

        } finally {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin");
            rd.forward(req, resp);
        }


    }
}
