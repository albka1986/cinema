package com.cinema.dto;

public class TicketDTO {
    private Integer Id;
    private Integer row;
    private Integer column;
    private UserDTO userDTO;
    private SessionDTO sessionDTO;
    private boolean check;

    public TicketDTO() {
    }

    public TicketDTO(Integer id, Integer row, Integer column, UserDTO userDTO, SessionDTO sessionDTO) {
        Id = id;
        this.row = row;
        this.column = column;
        this.userDTO = userDTO;
        this.sessionDTO = sessionDTO;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
