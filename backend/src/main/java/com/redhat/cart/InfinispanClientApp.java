package com.redhat.cart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.configuration.XMLStringConfiguration;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class InfinispanClientApp {

    @Inject
    RemoteCacheManager cacheManager;

    private static final String CACHE_CONFIG = "<distributed-cache name=\"%s\">"
          + " <encoding media-type=\"application/x-protostream\"/>"
          + "</distributed-cache>";


    void onStart(@Observes StartupEvent ev) {
        cacheManager.administration().getOrCreateCache("cartCache",
                new XMLStringConfiguration(String.format(CACHE_CONFIG, "cartCache")));
    }
}
