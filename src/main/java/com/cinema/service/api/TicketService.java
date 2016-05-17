package com.cinema.service.api;

import com.cinema.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    boolean[][] findAllTicketsBySessionId(Integer id);

    List<TicketDTO> findTicketsByUserId(Integer idUser);

    void buyTickets(TicketDTO ticketDTO);

    boolean isTicketEmpty(Integer idTickets, Integer row, Integer column);

}
