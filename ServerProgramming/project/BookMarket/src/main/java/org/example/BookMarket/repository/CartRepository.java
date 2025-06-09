package org.example.BookMarket.repository;

import org.example.BookMarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart); //장바구니 생성 메서드
    Cart read(String cartId); //장바구니 조회 메서드
    Cart remove(Cart cart); //장바구니 삭제 메서드
    Cart update(Cart cart); //장바구니 수정 메서드
}
