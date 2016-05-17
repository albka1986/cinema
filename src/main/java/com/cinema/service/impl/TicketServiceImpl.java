package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.TicketDAO;
import com.cinema.dao.impl.TicketDAOImpl;
import com.cinema.dto.TicketDTO;
import com.cinema.service.api.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    @Override
    public boolean[][] findAllTicketsBySessionId(Integer id) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        boolean[][] allTicketsBySessionId = ticketDAO.findAllTicketsBySessionId(id);
        return allTicketsBySessionId;
    }

    @Override
    public List<TicketDTO> findTicketsByUserId(Integer idUser) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        List<TicketDTO> ticketDTOs = Transformer.listTicketToTicketDto(ticketDAO.findTicketsByUserId(idUser));
        return ticketDTOs;
    }

    @Override
    public void buyTickets(TicketDTO ticketDTO) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        ticketDAO.buyTicket(Transformer.ticketDtoToTicket(ticketDTO));
    }

    @Override
    public boolean isTicketEmpty(Integer idTickets, Integer row, Integer column) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        boolean isTicketEmpty = ticketDAO.isTicketEmpty(idTickets, row, column);
        return isTicketEmpty;
    }


}
