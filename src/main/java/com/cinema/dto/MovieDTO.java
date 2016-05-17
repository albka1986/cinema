package com.cinema.dto;

public class MovieDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer duration;

    public MovieDTO() {
    }

    public MovieDTO(Integer id, String title, String description, Integer duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}' + "\n";
    }
}
