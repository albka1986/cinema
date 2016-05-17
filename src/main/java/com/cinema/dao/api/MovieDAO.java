package com.cinema.dao.api;

import com.cinema.model.Movie;

import java.util.List;

public interface MovieDAO {

    void createMovie(Movie movie);

    List<Movie> findAllMovies();

    Movie findMovieById(Integer id);

    Movie findMovieByTitle(String title);

    void deleteMovieById(Integer idMovie);
}
