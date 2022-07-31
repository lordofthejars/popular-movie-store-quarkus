package com.redhat.importer;

import java.util.List;

import javax.inject.Inject;

import com.redhat.importer.imdb.ImdbService;
import com.redhat.importer.repo.MongoDbRepository;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    ImdbService imdbService;

    @Inject
    MongoDbRepository mongoDbRepository;

    /* (non-Javadoc)
     * @see io.quarkus.runtime.QuarkusApplication#run(java.lang.String[])
     */
    @Override
    public int run(String... args) throws Exception {
        
        final List<MovieResources> movies = imdbService.getMovies();
        mongoDbRepository.add(movies);

        long numberOfMovies = mongoDbRepository.count();

        return numberOfMovies > 0 ? 0 : 1;
    }
    
}
