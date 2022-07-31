package com.redhat.cart;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import java.util.Objects;

public class MovieCart {
    private String movieId;
    private String title;
    private Quantity quantity;

    @ProtoFactory
    public MovieCart(String movieId, String title, Quantity quantity) {
        this.movieId = Objects.requireNonNull(movieId);
        this.title = Objects.requireNonNull(title);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public MovieCart() {
    }

    @ProtoField(number = 1)
    public String movieId() {
        return movieId;
    }

    @ProtoField(number = 2)
    public String title() {
        return title;
    }

    @ProtoField(number = 3)
    public Quantity quantity() {
        return quantity;
    }
}

