package com.redhat.cart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.infinispan.client.hotrod.RemoteCache;

import io.quarkus.infinispan.client.Remote;

@ApplicationScoped
public class InfinispanCart {
    
    @Inject
    @Remote("cartCache")
    RemoteCache<String, Cart> cache;

    public Cart getCart(String cartId) {
        return cache.get(cartId);
    }

    public void save(Cart cart) {
        cache.putIfAbsent(cart.cartId().getId(), cart);
    }

    public void update(Cart cart) {
        cache.replace(cart.cartId().getId(), cart);
    }

    public boolean exists(CartId cartId) {
        return cache.get(cartId.getId()) != null;
    }

    public void removeCart(CartId cartId) {
        cache.remove(cartId.getId());
    }
}
