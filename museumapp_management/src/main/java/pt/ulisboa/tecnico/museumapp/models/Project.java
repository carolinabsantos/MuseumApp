package pt.ulisboa.tecnico.museumapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Project implements Serializable {

    private Long id;
    private String title;
    private String type;
    private String color;
    private String description;
    private Integer days;
    private Double price;
    private Boolean featured;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime launchDate;

    public Project(Long id, String title, String type, String color, String description, Integer days, Double price, Boolean featured) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.description=description;
        this.days = days;
        this.price = price;
        this.featured = featured;
    }

    public Project(){}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDays() {
        return days;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", days=" + days +
                ", price=" + price +
                ", featured=" + featured +
                ", launchDate=" + launchDate +
                '}';
    }
    // constructor, getters, and setters removed for brevity
}
