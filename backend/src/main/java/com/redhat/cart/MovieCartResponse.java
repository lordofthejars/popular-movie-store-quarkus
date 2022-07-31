package com.redhat.cart;

import java.util.Objects;

public class MovieCartResponse {
    public String id;
    public String title;
    public Double price;
    public int quantity;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCartResponse)) {
            return false;
        }
        MovieCartResponse other = (MovieCartResponse) o;
        return Objects.equals(id, other.id)
                && Objects.equals(title, other.title)
                && Objects.equals(price, other.price)
                && Objects.equals(quantity, other.quantity);
    }
}

