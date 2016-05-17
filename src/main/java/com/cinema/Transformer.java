package com.cinema;

import com.cinema.dto.MovieDTO;
import com.cinema.dto.SessionDTO;
import com.cinema.dto.TicketDTO;
import com.cinema.dto.UserDTO;
import com.cinema.model.Movie;
import com.cinema.model.Session;
import com.cinema.model.Ticket;
import com.cinema.model.User;

import java.util.LinkedList;
import java.util.List;

public class Transformer {

    public static UserDTO userToUserDto(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    public static User userDtoToUser(UserDTO userDto) {

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setBirthday(userDto.getBirthday());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static List<UserDTO> listUserToListUserDto(List<User> users) {
        List<UserDTO> userDTOs = new LinkedList<>();
        for (User user : users) {
            UserDTO userDTO = userToUserDto(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public static MovieDTO movieToMovieDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setDuration(movie.getDuration());
        return movieDTO;
    }

    public static Movie movieDtoToMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setDuration(movieDTO.getDuration());
        return movie;
    }

    public static List<MovieDTO> listMovieToMovieDto(List<Movie> movies) {
        List<MovieDTO> movieDTOs = new LinkedList<>();
        for (Movie movie : movies) {
            MovieDTO movieDTO = movieToMovieDto(movie);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public static SessionDTO sessionToSessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(session.getId());
        sessionDTO.setMovieDTO(movieToMovieDto(session.getMovie()));
        sessionDTO.setSessionDate(session.getSessionDate());
        return sessionDTO;
    }

    public static Session sessionDtoToSession(SessionDTO sessionDTO) {
        Session session = new Session();
        session.setId(sessionDTO.getId());
        session.setMovie(movieDtoToMovie(sessionDTO.getMovieDTO()));
        session.setSessionDate(sessionDTO.getSessionDate());
        return session;
    }

    public static List<SessionDTO> listSessionToSessionDto(List<Session> sessions) {
        List<SessionDTO> sessionDTOs = new LinkedList<>();
        for (Session session : sessions) {
            SessionDTO sessionDTO = sessionToSessionDTO(session);
            sessionDTOs.add(sessionDTO);
        }
        return sessionDTOs;
    }

    public static TicketDTO ticketToTicketDto(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setRow(ticket.getRow());
        ticketDTO.setColumn(ticket.getColumn());
        ticketDTO.setUserDTO(Transformer.userToUserDto(ticket.getUser()));
        ticketDTO.setSessionDTO(Transformer.sessionToSessionDTO(ticket.getSession()));
        return ticketDTO;
    }

    public static Ticket ticketDtoToTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setRow(ticketDTO.getRow());
        ticket.setColumn(ticketDTO.getColumn());
        ticket.setUser(Transformer.userDtoToUser(ticketDTO.getUserDTO()));
        ticket.setSession(Transformer.sessionDtoToSession(ticketDTO.getSessionDTO()));
        return ticket;
    }

    public static List<TicketDTO> listTicketToTicketDto(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOs = new LinkedList<>();
        for (Ticket ticket : tickets) {
            TicketDTO ticketDTO = ticketToTicketDto(ticket);
            ticketDTOs.add(ticketDTO);
        }
        return ticketDTOs;
    }



}