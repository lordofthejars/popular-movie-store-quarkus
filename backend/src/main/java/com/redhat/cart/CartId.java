package com.redhat.cart;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;
import java.util.UUID;

public class CartId {
    private final String id;

    @ProtoFactory
    public CartId(String id) {
        this.id = id;
    }

    public CartId() {
        this.id = UUID.randomUUID().toString();
    }

    @ProtoField(number = 1)
    public String getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartId)) {
            return false;
        }
        CartId other = (CartId) o;
        return Objects.equals(id, other.id);
    }
}

