package com.cinema.web;

import com.cinema.dto.SessionDTO;
import com.cinema.model.Hall;
import com.cinema.service.api.SessionService;
import com.cinema.service.api.TicketService;
import com.cinema.service.impl.SessionServiceImpl;
import com.cinema.service.impl.TicketServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idSession = Integer.parseInt(request.getParameter("idSession"));
        TicketService ticketService = new TicketServiceImpl();
        boolean[][] allTicketsBySessionId = ticketService.findAllTicketsBySessionId(idSession);
        List<Integer> columns = new LinkedList<>();
        List<Integer> rows = new LinkedList<>();
        for (int i = 0; i < (Hall.getColumns() + 1); i++) {
            if (i == 0) {
                columns.add(null);
            } else {
                columns.add(i);
            }
        }
        for (int i = 1; i < (Hall.getRows() + 1); i++) {
            rows.add(i);
        }
        SessionService sessionService = new SessionServiceImpl();
        SessionDTO sessionDTO = sessionService.findSessionById(idSession);


        request.getSession().setAttribute("rowsAmount", Hall.getRows());
        request.getSession().setAttribute("columnsAmount", Hall.getColumns());
        request.getSession().setAttribute("idSession", sessionDTO.getId());
        request.getSession().setAttribute("sessionDate", sessionDTO.getSessionDate());
        request.getSession().setAttribute("sessionMovieTitle", sessionDTO.getMovieDTO().getTitle());
        request.getSession().setAttribute("allTicketsBySessionId", allTicketsBySessionId);
        request.getSession().setAttribute("columns", columns);
        request.getSession().setAttribute("rows", rows);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/tickets.jsp");
        rd.forward(request, response);
    }

}
