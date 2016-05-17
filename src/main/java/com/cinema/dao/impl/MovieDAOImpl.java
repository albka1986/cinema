package com.cinema.dao.impl;

import com.cinema.dao.api.MovieDAO;
import com.cinema.datasource.DataSource;
import com.cinema.model.Movie;

import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public void createMovie(Movie movie) {
        DataSource.createMovie(movie);
    }

    @Override
    public List<Movie> findAllMovies() {
        List<Movie> movies = DataSource.findAllMovies();
        return movies;
    }

    @Override
    public Movie findMovieById(Integer id) {
        Movie movie = DataSource.findMovieById(id);
        return movie;
    }

    @Override
    public Movie findMovieByTitle(String title) {
        Movie movie = DataSource.findMovieByTitle(title);
        return movie;
    }

    @Override
    public void deleteMovieById(Integer idMovie) {
        DataSource.deleteMovieById(idMovie);
    }


}
