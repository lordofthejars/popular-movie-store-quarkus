package com.redhat.movies;

import java.util.List;

public class Movies {

    public int page;
    public List<Movie> results;
    public int total_pages;
    public int total_results;

    public Movies(List<Movie> movies) {
        this.results = movies;
        this.page = 1;
        this.total_pages = 1;
        this.total_results = movies.size();
    }

}
