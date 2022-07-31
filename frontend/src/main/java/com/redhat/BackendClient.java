package com.redhat;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient
public interface BackendClient {
    
    @GET
    @Path("/movies/{movieId}")
    public Response getMovieById(@PathParam("movieId") String movieId);

    @POST
    @Path("/movies")
    public Response getMovies(MovieControllerRequest request);

    @GET
    @Path("/cart/{cartId}")
    public Response getCart(@PathParam("cartId") String cartId);
    
    @POST
    @Path("/cart")
    public Response postCart();

    @DELETE
    @Path("/cart/{cartId}")
    public Response removeCart(@PathParam("cartId") String cartId);

    @POST
    @Path("/cart/addMovie")
    public Response addMovieCart(CartMovieRequest request);

    @DELETE
    @Path("/cart/removeMovie")
    public Response removeMovieCart(CartMovieRequest request);

}
