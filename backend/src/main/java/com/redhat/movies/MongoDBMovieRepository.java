package com.redhat.movies;

import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Filters.in;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.CustomException;

@ApplicationScoped
public class MongoDBMovieRepository {
    
    @Inject
    MongoClient mongoClient;

    public List<Movie> getMovies() {
        final FindIterable<Document> allMovies = getCollection().find();
        final List<Movie> movies = new ArrayList<>();
        allMovies
            .map(Movie::new)
            .forEach(m ->
                movies.add(m)
        );

        return movies;
    }

    public List<Movie> getMovies(String searchTerm) {
        final FindIterable<Document> filteredMovies = getCollection().find(regex("title", ".*" + searchTerm + ".*"));
        final List<Movie> movies = new ArrayList<>();

        filteredMovies
            .map(Movie::new)
            .forEach(m ->
                movies.add(m)
        );

        return movies;
    }

    public List<Movie> findMoviesByMovieIds(List<String> movieIds) {

        final FindIterable<Document> filteredMovies = getCollection().find(in("id", movieIds));
        final List<Movie> movies = new ArrayList<>();

        filteredMovies
            .map(Movie::new)
            .forEach(m -> movies.add(m));

        return movies;

    }

    public Movie findMovieById(String id) {
        final Document movie = getCollection().find(new Document("id", id))
                            .first();

        if (movie == null) {
            throw new CustomException("Movie " + id + " not found.", 404);
        }

        return new Movie(movie);
    }

    public boolean existsMovie(String id) {
        try {
            findMovieById(id);
            return true;
        } catch (CustomException e) {
            return false;
        }
    }

    private MongoCollection<Document> getCollection(){
        return mongoClient
                .getDatabase("movies")
                .getCollection("movies");
    }

}
