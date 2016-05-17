package com.cinema.model;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Session {
    private Integer id;
    private Movie movie;
    private LocalDateTime sessionDate;
    private boolean[][] tickets;

    public Session() {
    }

    public Session(Integer id, Movie movie, LocalDateTime sessionDate, boolean[][] tickets) {
        this.id = id;
        this.movie = movie;
        this.sessionDate = sessionDate;
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public boolean[][] getTickets() {
        return tickets;
    }

    public void setTickets(boolean[][] tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", movie=" + movie +
                ", sessionDate=" + sessionDate +
                ", tickets=" + Arrays.toString(tickets) +
                '}';
    }
}
