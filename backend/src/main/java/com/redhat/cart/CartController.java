package com.redhat.cart;



import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.movies.MongoDBMovieRepository;
import com.redhat.movies.Movie;

import java.util.List;

@Path("/cart")
public class CartController {

    @Inject
    InfinispanCart infinispanCart;

    @Inject
    MongoDBMovieRepository mongoDBMovieRepository;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");

    @GET
    @Path("{cartId}")
    public CartResponse getCart(@PathParam("cartId") String cartId) {
        Cart cart = this.infinispanCart.getCart(cartId);
        System.out.println(cart.moviesList().getMovieIds());
        List<Movie> movies = this.mongoDBMovieRepository.findMoviesByMovieIds(cart.moviesList().getMovieIds());
        CartResponse cartResponse = new CartResponse(cart, movies);
        cartResponse.hostname = HOSTNAME;

        return cartResponse;
    }

    @POST
    public Response postCart() {
        Cart cart = new Cart(
                new CartId(),
                new MovieCollection()
        );

        infinispanCart.save(cart);

        ObjectNode id = OBJECT_MAPPER.createObjectNode().put("cartId", cart.cartId().getId());
        return Response.status(200).entity(id).build();
    }

    @DELETE
    @Path("{cartId}")
    public Response removeCart(@PathParam("cartId") String cartId) {
        infinispanCart.removeCart(new CartId(cartId));
        return Response.status(200).build();
    }

    @POST
    @Path("/addMovie")
    public Response addMovieCart(CartMovieRequest request) {
        final Cart cart = infinispanCart.getCart(request.cartId);

        if (cart == null) {
            return Response.status(500).entity("Cart " + request.cartId + " not found").build();
        }
        
        final Movie movie = mongoDBMovieRepository.findMovieById(request.movieId);
        
        System.out.println(movie.getTitle());

        cart.moviesList().addMovie(
                new MovieCart(movie.getId(), movie.getTitle(), new Quantity(1))
        );
        
        this.infinispanCart.update(cart);

        System.out.println("Inserted " + cart.moviesList().getMovieIds());

        return Response.status(200).build();
    }

    @DELETE
    @Path("/removeMovie")
    public Response removeMovieCart(CartMovieRequest request) {
        final Cart cart = infinispanCart.getCart(request.cartId);

        if (cart == null) {
            return Response.status(500).entity("Cart " + request.cartId + " not found").build();
        }

        final Movie movie = mongoDBMovieRepository.findMovieById(request.movieId);

        cart.moviesList().removeMovie(movie.getId());

        return Response.status(200).build();
    }
}
