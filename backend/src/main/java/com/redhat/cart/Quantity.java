package com.redhat.cart;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Quantity {
    private Integer quantity;

    @ProtoFactory
    public Quantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Quantity(){
        this.quantity = 1;
    }

    @ProtoField(number = 1)
    public Integer getQuantity() {
        return quantity;
    }

    public void addQuantity(int plusValue){
        this.quantity += plusValue;
    }
}

