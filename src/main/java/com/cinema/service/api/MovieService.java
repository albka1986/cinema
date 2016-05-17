package com.cinema.service.api;

import com.cinema.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    void createMovie(MovieDTO movieDTO);

    List<MovieDTO> findAllMovies();

    MovieDTO findMovieById(Integer id);

    MovieDTO findMovieByTitle(String title);

    void deleteMovieById(Integer idMovie);
}
