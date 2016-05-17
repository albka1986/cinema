package com.cinema.web;

import com.cinema.dto.SessionDTO;
import com.cinema.dto.TicketDTO;
import com.cinema.dto.UserDTO;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketService ticketService = new TicketServiceImpl();

        try {
            int idSession = Integer.parseInt(req.getParameter("idSession"));
            int column = Integer.parseInt(req.getParameter("column"));
            int row = Integer.parseInt(req.getParameter("row"));


            HttpSession session = req.getSession(false);

            if (session.getAttribute("user") != null) {
                if (ticketService.isTicketEmpty(idSession, row, column)) {
                    TicketDTO ticketDTO = new TicketDTO();
                    ticketDTO.setRow(row);
                    ticketDTO.setColumn(column);

                    UserDTO userDTO = (UserDTO) session.getAttribute("user");
                    ticketDTO.setUserDTO(userDTO);

                    SessionService sessionService = new SessionServiceImpl();
                    SessionDTO sessionDTO = sessionService.findSessionById(idSession);
                    ticketDTO.setSessionDTO(sessionDTO);

                    ticketService.buyTickets(ticketDTO);

                    String message = "You bought the ticket!";
                    req.getSession().setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/TicketServlet");
                    rd.forward(req, resp);

                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<html>" +
                            "<body>" +
                            "<center>" +
                            "<h2>" +
                            "<font color=\"red\">The seat is occupied!</font>" +
                            "</h2>" +
                            "</center>" +
                            "</body>" +
                            "</<html>");
                    RequestDispatcher rs = req.getRequestDispatcher("/tickets.jsp");
                    rs.include(req, resp);
                }
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<html>" +
                        "<body>" +
                        "<center>" +
                        "<h2>" +
                        "<font color=\"red\">Please login first!</font>" +
                        "</h2>" +
                        "</center>" +
                        "</body>" +
                        "</<html>");
                RequestDispatcher rs = req.getRequestDispatcher("/tickets.jsp");
                rs.include(req, resp);
            }
        } catch (NumberFormatException e) {
            PrintWriter out = resp.getWriter();
            out.println("<html>" +
                    "<body>" +
                    "<center>" +
                    "<h2>" +
                    "<font color=\"red\">Error! Try again!</font>" +
                    "</h2>" +
                    "</center>" +
                    "</body>" +
                    "</<html>");
            RequestDispatcher rs = req.getRequestDispatcher("/tickets.jsp");
            rs.include(req, resp);
        }

    }

}
