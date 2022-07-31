package com.redhat.cart;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = { Cart.class, CartId.class, MovieCart.class, MovieCollection.class, Quantity.class }, schemaPackageName = "movie_cart")
interface CartSchema extends GeneratedSchema {
}
