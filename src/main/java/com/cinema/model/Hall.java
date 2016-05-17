package com.cinema.model;

public class Hall {
    private Integer id;
    private String name;
    private static final Integer rows = 15; //кол-во рядов в зале
    private static final Integer columns = 15; //кол-во мест в ряду

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Integer getRows() {
        return rows;
    }

    public static Integer getColumns() {
        return columns;
    }
}
