package org.example.BookMarket.service;

import org.example.BookMarket.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
}
