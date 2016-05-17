package com.cinema.dto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SessionDTO {
    private Integer id;
    private MovieDTO movieDTO;
    private LocalDateTime sessionDate;
    private boolean[][] ticketDTOs;

    public SessionDTO() {
    }

    public SessionDTO(Integer id, MovieDTO movieDTO, LocalDateTime sessionDate, boolean[][] ticketDTOs) {
        this.id = id;
        this.movieDTO = movieDTO;
        this.sessionDate = sessionDate;
        this.ticketDTOs = ticketDTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovieDTO getMovieDTO() {
        return movieDTO;
    }

    public void setMovieDTO(MovieDTO movieDTO) {
        this.movieDTO = movieDTO;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public boolean[][] getTicketDTOs() {
        return ticketDTOs;
    }

    public void setTicketDTOs(boolean[][] ticketDTOs) {
        this.ticketDTOs = ticketDTOs;
    }

    @Override
    public String toString() {
        return "SessionDTO{" +
                "id=" + id +
                ", movieDTO=" + movieDTO +
                ", sessionDate=" + sessionDate +
                ", ticketDTOs=" + Arrays.toString(ticketDTOs) +
                '}';
    }
}
