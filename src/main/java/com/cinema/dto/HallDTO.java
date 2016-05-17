package com.cinema.dto;

public class HallDTO {
    private Integer id;
    private String name;
    private static final Integer rowCount = 15;
    private static final Integer columnCount = 15;

    public HallDTO() {
    }

    public HallDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public Integer getRowCount() {
        return rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }
}
