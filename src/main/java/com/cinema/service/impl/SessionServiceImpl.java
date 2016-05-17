package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.SessionDAO;
import com.cinema.dao.impl.SessionDAOImpl;
import com.cinema.dto.SessionDTO;
import com.cinema.service.api.SessionService;

import java.util.List;

public class SessionServiceImpl implements SessionService {
    @Override
    public void createSession(SessionDTO sessionDTO) {
        SessionDAO sessionDAO = new SessionDAOImpl();
        sessionDAO.createSession(Transformer.sessionDtoToSession(sessionDTO));
    }

    @Override
    public List<SessionDTO> findAllSessions() {
        SessionDAO sessionDAO = new SessionDAOImpl();
        List<SessionDTO> sessionDTOs = Transformer.listSessionToSessionDto(sessionDAO.findAllSessions());
        return sessionDTOs;
    }

    @Override
    public List<SessionDTO> findSessionsByMovieId(Integer movieId) {
        SessionDAO sessionDAO = new SessionDAOImpl();
        List<SessionDTO> sessionDTOs = Transformer.listSessionToSessionDto(sessionDAO.findSessionByMovieId(movieId));
        return sessionDTOs;
    }

    @Override
    public SessionDTO findSessionById(Integer idSession) {
        SessionDAO sessionDAO = new SessionDAOImpl();
        SessionDTO sessionDTO = Transformer.sessionToSessionDTO(sessionDAO.findSessionById(idSession));
        return sessionDTO;
    }

    @Override
    public void deleteSessionById(Integer idSession) {
        SessionDAO sessionDAO = new SessionDAOImpl();
        sessionDAO.deleteSessionById(idSession);
    }
}
