package com.cinema.dao.api;

import com.cinema.model.Ticket;

import java.util.List;

public interface TicketDAO {
    boolean[][] findAllTicketsBySessionId(Integer id);

    List<Ticket> findTicketsByUserId(Integer idUser);

    void buyTicket(Ticket ticket);

    boolean isTicketEmpty(Integer idTickets, Integer row, Integer column);
}
