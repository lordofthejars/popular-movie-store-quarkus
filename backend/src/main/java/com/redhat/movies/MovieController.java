package com.redhat.movies;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/movies")
public class MovieController {

    @Inject
    MongoDBMovieRepository mongoDBMovieRepository;


    @GET
    @Path("{movieId}")
    public Movie getMovieById(@PathParam("movieId") String movieId) {
        return mongoDBMovieRepository.findMovieById(movieId);
    }

    @POST
    public Movies getMovies(MovieControllerRequest request) {
        
        List<Movie> movies = new ArrayList<>();

        if(!"".equals(request.searchTearm)) {
            movies.addAll(mongoDBMovieRepository.getMovies(request.searchTearm));
        } else {
            movies.addAll(mongoDBMovieRepository.getMovies());
        }
        
        return new Movies(movies);
    }
}
