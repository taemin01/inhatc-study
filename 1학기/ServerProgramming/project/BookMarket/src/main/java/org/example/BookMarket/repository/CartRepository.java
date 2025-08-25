package org.example.BookMarket.repository;

import org.example.BookMarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart); //장바구니 생성 메서드
    Cart read(String cartId); //장바구니 조회 메서드
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
