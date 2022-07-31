package com.redhat.cart;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import java.util.Objects;

public class Cart {
    private CartId cartId;
    private MovieCollection moviesList;

    @ProtoFactory
    public Cart(CartId cartId, MovieCollection moviesList) {
        this.cartId = Objects.requireNonNull(cartId);
        this.moviesList = Objects.requireNonNull(moviesList);
    }

    public Cart() {
    }

    @ProtoField(number = 1)
    public CartId cartId() {
        return cartId;
    }

    @ProtoField(number = 2)
    public MovieCollection moviesList() {
        return moviesList;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) o;
        return Objects.equals(cartId, other.cartId);
    }
}

