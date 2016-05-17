package com.cinema.dao.impl;

import com.cinema.dao.api.TicketDAO;
import com.cinema.datasource.DataSource;
import com.cinema.model.Ticket;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public boolean[][] findAllTicketsBySessionId(Integer id) {
        boolean[][] allTicketsBySessionId = DataSource.findTicketsBySessionId(id);
        return allTicketsBySessionId;
    }

    @Override
    public List<Ticket> findTicketsByUserId(Integer idUser) {
        List<Ticket> tickets = DataSource.findTicketsByUserId(idUser);
        return tickets;
    }

    @Override
    public void buyTicket(Ticket ticket) {
        DataSource.buyTicket(ticket);
    }

    @Override
    public boolean isTicketEmpty(Integer idTickets, Integer row, Integer column) {
        boolean isTicketEmpty = DataSource.isTicketEmpty(idTickets, row, column);
        return isTicketEmpty;
    }
}
