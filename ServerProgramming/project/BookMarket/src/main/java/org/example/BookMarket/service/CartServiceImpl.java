package org.example.BookMarket.service;

import org.example.BookMarket.domain.Cart;
import org.example.BookMarket.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart read(String cartId) {
        return cartRepository.read(cartId);
    }

    @Override
    public Cart create(Cart cart) { //저장소 객체에서 장바구니 ID에 등록된 모든 정보 가져와 반환하는 메서드.
        return cartRepository.create(cart);
    }

    @Override
    public void update(String cartId, Cart cart) {
        cartRepository.update(cartId, cart);
    }

    @Override
    public void delete(String cardId) {
        cartRepository.delete(cardId);
    }

    @Override
    public Cart validateCart(String cartId) {
        return null;
    }
}
