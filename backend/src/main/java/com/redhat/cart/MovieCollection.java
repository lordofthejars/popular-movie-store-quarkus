package com.redhat.cart;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MovieCollection {

    private Collection<MovieCart> movies;

    @ProtoFactory
    public MovieCollection(Collection<MovieCart> movies) {
        this.movies = movies;
    }

    public MovieCollection() {
        this.movies = new ArrayList<>();
    }

    @ProtoField(number = 1)
    public Collection<MovieCart> movies() {
        return movies;
    }

    public void addMovie(MovieCart movie){
        if(this.movies.stream().anyMatch(x -> Objects.equals(x.movieId(), movie.movieId()))){
            this.movies.stream().filter(c -> movie.movieId().equals(c.movieId()))
                .forEach(m -> m.quantity().addQuantity(1));
            return;
        }

        System.out.println("Adding movie");
        this.movies.add(movie);
    }

    public void removeMovie(String movieId){
        if(this.movies.stream().anyMatch(x -> Objects.equals(x.movieId(), movieId))){
            AtomicBoolean flagConcurrent = new AtomicBoolean(false);
            this.movies.stream().filter(c -> movieId.equals(c.movieId()))
                .forEach(m -> {
                    if (m.quantity().getQuantity() > 1){
                        m.quantity().addQuantity(-1);
                        return;
                    }
                    flagConcurrent.set(true);
                });

            if(flagConcurrent.get()){
                this.movies.removeIf(c -> movieId.equals(c.movieId()));
            }
        }
    }

    public void removeAll(){
        this.movies = new ArrayList<>();
    }

    public void addAll(Collection<MovieCart> movies){
        this.movies.addAll(movies);
    }

    public List<String> getMovieIds(){
        List<String> result = new ArrayList<String>();
        this.movies.forEach(movieCart -> result.add(movieCart.movieId()));
        return result;
    }
}

