package org.example.BookMarket.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class Cart {
    private String cartId; //장바구니 ID
    private Map<String, CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart() {
        cartItems = new HashMap<String, CartItem>();
        grandTotal = new BigDecimal(0);
    }

    public Cart(String CartId) {
        this(); //같은 클래스 내에 다른 생성자 호출 지금은 값을 안 넣었기에 기본 생성자 호출
        this.cartId = CartId;
    }
}
