package com.redhat.importer.repo;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.importer.MovieResources;

@ApplicationScoped
public class MongoDbRepository {

    private static final Random random = new Random();

    @Inject MongoClient mongoClient;


    public long count() {
        return getCollection().countDocuments();
    }

    public void add(List<MovieResources> movieResources) {
        movieResources.forEach(mr -> 
        {
            Document document = new Document();

            document.append("id", mr.poster_path.substring(1, mr.poster_path.length()-4))
                    .append("title", mr.title)
                    .append("cover", "https://image.tmdb.org/t/p/w500" + mr.poster_path)
                    .append("price", generatePrice())
                    .append("description", mr.overview)
                    .append("genreName", mr.genre_name)
                    .append("rating", mr.vote_average)
                    .append("year", mr.release_date);


            getCollection().insertOne(document);
        });
    }

    private double generatePrice() {
        int min = 0;
        int max = 9;

        int value = random.nextInt(max + min) + min;
        return (double) ((double) value + 0.99);
    }

    private MongoCollection getCollection(){
        return mongoClient
                .getDatabase("movies")
                .getCollection("movies");
    }
    
}
