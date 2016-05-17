package com.cinema.model;

public class Ticket {
    private Integer Id;
    private Integer row;
    private Integer column;
    private User user;
    private Session session;
    private boolean check;

    public Ticket() {
    }

    public Ticket(Integer id, Integer row, Integer column, User user, Session session) {
        Id = id;
        this.row = row;
        this.column = column;
        this.user = user;
        this.session = session;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
