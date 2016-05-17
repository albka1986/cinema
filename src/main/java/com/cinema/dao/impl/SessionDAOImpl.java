package com.cinema.dao.impl;

import com.cinema.dao.api.SessionDAO;
import com.cinema.datasource.DataSource;
import com.cinema.model.Session;

import java.util.List;

public class SessionDAOImpl implements SessionDAO {

    @Override
    public void createSession(Session session) {
        DataSource.createSession(session);
    }

    @Override
    public List<Session> findAllSessions() {
        List<Session> sessions = DataSource.findAllSessions();
        return sessions;
    }

    @Override
    public List<Session> findSessionByMovieId(Integer movieId) {
        List<Session> sessions = DataSource.findSessionsByMovieId(movieId);
        return sessions;
    }

    @Override
    public Session findSessionById(Integer idSession) {
        return DataSource.findSessionById(idSession);
    }

    @Override
    public void deleteSessionById(Integer idSession) {
        DataSource.deleteSessionById(idSession);
    }
}
