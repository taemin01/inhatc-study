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

    public void updateGrandTotal() {
        BigDecimal total = BigDecimal.ZERO; // 초기값 0
        for(CartItem item : cartItems.values()) { // 장바구니의 모든 아이템에 대해 반복
            BigDecimal itemTotal = item.getBook().getUnitPrice() // 도서 단가
                    .multiply(new BigDecimal(item.getQuantity())); // 수량 곱하기
            total = total.add(itemTotal); // 총합에 더하기
        }
        this.grandTotal = total; // grandTotal 필드에 저장
    }


    public Cart() {
        cartItems = new HashMap<String, CartItem>();
        grandTotal = new BigDecimal(0);
    }

    public Cart(String CartId) {
        this(); //같은 클래스 내에 다른 생성자 호출 지금은 값을 안 넣었기에 기본 생성자 호출
        this.cartId = CartId;
    }

    public void addCartItem(CartItem item) {
        String bookId = item.getBook().getBookId();
        if(cartItems.containsKey(bookId)) {
            CartItem cartItem = cartItems.get(bookId);
            cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());
            cartItems.put(bookId, cartItem);
        } else {
            cartItems.put(bookId, item);
        }
        updateGrandTotal();
    }

    public void removeCartItem(CartItem item) {
        String bookId = item.getBook().getBookId();
        cartItems.remove(bookId);
        updateGrandTotal();
    }
}
