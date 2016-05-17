package com.cinema.dao.api;

import com.cinema.model.Session;

import java.util.List;

public interface SessionDAO {
    void createSession(Session session);

    List<Session> findAllSessions();

    List<Session> findSessionByMovieId(Integer movieId);

    Session findSessionById(Integer idSession);

    void deleteSessionById(Integer idSession);
}


