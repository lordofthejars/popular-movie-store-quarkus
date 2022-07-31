package com.redhat.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.redhat.movies.Movie;

public class CartResponse {
    public String cartId;
    public List<MovieCartResponse> moviesList;
    public Double totalPrice;
    public String hostname;

    public CartResponse(Cart cart, List<Movie> movies) {
        this.cartId = cart.cartId().getId();
        this.totalPrice = 0.0;

        int index = 0;
        List<MovieCartResponse> movieList = new ArrayList<>();
        for (MovieCart movie : cart.moviesList().movies()) {
            MovieCartResponse movieResponse = new MovieCartResponse();
            movieResponse.id = movie.movieId();
            movieResponse.title = movie.title();
            movieResponse.price = movies.get(index).getPrice();
            movieResponse.quantity = movie.quantity().getQuantity();
            movieList.add(movieResponse);
            totalPrice += movieResponse.price * movie.quantity().getQuantity();
            index++;
        }
        this.moviesList = movieList;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartResponse)) {
            return false;
        }
        CartResponse other = (CartResponse) o;
        return Objects.equals(cartId, other.cartId)
                && Objects.equals(totalPrice, other.totalPrice)
                && Objects.equals(moviesList, other.moviesList);
    }
}
