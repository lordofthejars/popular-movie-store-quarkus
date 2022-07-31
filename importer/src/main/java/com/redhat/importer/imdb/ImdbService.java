package com.redhat.importer.imdb;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.redhat.importer.MovieResources;
import com.redhat.importer.ResultMovieGenresResources;
import com.redhat.importer.ResultMoviesResources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@ApplicationScoped
public class ImdbService {

    @ConfigProperty(name = "api.moviedb.key")
    String api_key;

    @ConfigProperty(name = "movie.host", defaultValue = "https://api.themoviedb.org/3/movie/popular")
    String movieHost;

    @ConfigProperty(name = "genre.host", defaultValue = "https://api.themoviedb.org/3/genre/movie/list")
    String genreHost;

    private final Client client = ClientBuilder.newClient();

    public List<MovieResources> getMovies() {
        final ResultMovieGenresResources genresResources = getGenres();
        
        final List<MovieResources> result = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            final ResultMoviesResources response = client.target(movieHost)
                .queryParam("api_key", api_key)
                .queryParam("page",i)
                .request(MediaType.APPLICATION_JSON)
                .get(ResultMoviesResources.class);
            result.addAll(response.results);
            
            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }
        }

        result.forEach((movie) -> {
            genresResources.genres.forEach((genre) -> {
                if(!movie.genre_ids.isEmpty() && genre.id == movie.genre_ids.get(0)){
                    movie.genre_name = genre.name;
                }
            });
        });

        return result;
    }

    private ResultMovieGenresResources getGenres() {
        return client.target(genreHost)
            .queryParam("api_key", api_key)
            .request(MediaType.APPLICATION_JSON)
            .get(ResultMovieGenresResources.class);
    }
}
