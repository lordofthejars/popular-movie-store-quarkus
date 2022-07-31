package com.redhat.movies;

import java.util.Date;
import java.util.Objects;

import org.bson.Document;

public class Movie {
    private String id;
    private String cover;
    private String title;
    private String genreName;
    private double price;
    private Date year;
    private double rating;
    private String description;

    public Movie(Document document) {
        this.id = document.getString("id");
        this.title = document.getString("title");
        this.cover = document.getString("cover");
        this.genreName = document.getString("genreName");
        this.price = document.getDouble("price");
        this.description = document.getString("description");
        this.rating = document.getDouble("rating");
        this.year = document.getDate("year");
    }

    public Movie(String id, String cover, String title, String genre, double price, Date year, double rating, String description) {
        this.id = id;
        this.cover = cover;
        this.title = title;
        this.genreName = genre;
        this.price = price;
        this.year = year;
        this.rating = rating;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getGenreName() {
        return genreName;
    }

    public double getPrice() {
        return price;
    }

    public Date getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) o;
        return Objects.equals(id, other.id)
                && Objects.equals(cover, other.cover)
                && Objects.equals(title, other.title)
                && Objects.equals(genreName, other.genreName)
                && price == other.price
                && rating == other.rating
                && Objects.equals(description, other.description);
    }
}
