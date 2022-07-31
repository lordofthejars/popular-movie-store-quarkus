package com.redhat;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api")
public class CartController {

    @RestClient
    BackendClient cartClient;

    @GET
    @Path("/movies/{movieId}")
    public Response getMovieById(@PathParam("movieId") String movieId) {
        return cartClient.getMovieById(movieId);
    }

    @POST
    @Path("/movies")
    public Response getMovies(MovieControllerRequest request) {
        return cartClient.getMovies(request);
    }

    @GET
    @Path("/cart/{cartId}")
    public Response getCart(@PathParam("cartId") String cartId) {
        return cartClient.getCart(cartId);
    }

    @POST
    @Path("/cart")
    public Response postCart() {
        return cartClient.postCart();
    }

    @DELETE
    @Path("/cart/{cartId}")
    public Response removeCart(@PathParam("cartId") String cartId) {
        return cartClient.removeCart(cartId);
    }

    @POST
    @Path("/cart/addMovie")
    public Response addMovieCart(CartMovieRequest request) {
        return cartClient.addMovieCart(request);
    }

    @DELETE
    @Path("/cart/removeMovie")
    public Response removeMovieCart(CartMovieRequest request) {
        return cartClient.removeMovieCart(request);
    }
}
