package com.tutexpsoft.ecommercedev.model;

import java.util.UUID;

/**
 * Created by noushad on 11/30/17.
 */

public class CartItem {

    private UUID id;

    public CartItem() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
