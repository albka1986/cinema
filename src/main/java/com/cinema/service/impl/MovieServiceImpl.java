package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.MovieDAO;
import com.cinema.dao.impl.MovieDAOImpl;
import com.cinema.dto.MovieDTO;
import com.cinema.model.Movie;
import com.cinema.service.api.MovieService;

import java.util.List;


public class MovieServiceImpl implements MovieService {

    @Override
    public void createMovie(MovieDTO movieDTO) {
        MovieDAO movieDAO = new MovieDAOImpl();
        Movie movie = Transformer.movieDtoToMovie(movieDTO);
        movieDAO.createMovie(movie);
    }

    @Override
    public List<MovieDTO> findAllMovies() {
        MovieDAO movieDAO = new MovieDAOImpl();
        List<MovieDTO> movieDTOs = Transformer.listMovieToMovieDto(movieDAO.findAllMovies());
        return movieDTOs;
    }

    @Override
    public MovieDTO findMovieById(Integer id) {
        MovieDAO movieDAO = new MovieDAOImpl();
        MovieDTO movieDTO = Transformer.movieToMovieDto(movieDAO.findMovieById(id));
        return movieDTO;
    }

    @Override
    public MovieDTO findMovieByTitle(String title) {
        MovieDAO movieDAO = new MovieDAOImpl();
        MovieDTO movieDTO = Transformer.movieToMovieDto(movieDAO.findMovieByTitle(title));
        return movieDTO;
    }

    @Override
    public void deleteMovieById(Integer idMovie) {
        MovieDAO movieDAO = new MovieDAOImpl();
        movieDAO.deleteMovieById(idMovie);
    }
}
