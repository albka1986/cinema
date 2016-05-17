package com.cinema.web;

import com.cinema.dto.TicketDTO;
import com.cinema.dto.UserDTO;
import com.cinema.service.api.TicketService;
import com.cinema.service.impl.TicketServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        TicketService ticketService = new TicketServiceImpl();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<TicketDTO> ticketDTOs = ticketService.findTicketsByUserId(userDTO.getId());

        session.setAttribute("ticketDTOs", ticketDTOs);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        TicketService ticketService = new TicketServiceImpl();
        List<TicketDTO> ticketDTOs = ticketService.findTicketsByUserId(userDTO.getId());

        session.setAttribute("ticketDTOs", ticketDTOs);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
        rd.forward(req, resp);
    }
}
