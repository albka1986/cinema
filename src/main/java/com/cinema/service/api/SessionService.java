package com.cinema.service.api;

import com.cinema.dto.SessionDTO;

import java.util.List;

public interface SessionService {
    void createSession(SessionDTO sessionDTO);

    List<SessionDTO> findAllSessions();

    List<SessionDTO> findSessionsByMovieId(Integer movieId);

    SessionDTO findSessionById(Integer idSession);

    void deleteSessionById(Integer idSession);

}
