package org.example.BookMarket.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders") // 테이블 이름을 orders로 생성
@Data
public class Order {
    @Id
    @GeneratedValue // 주문 아이디
    private Long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer; // 고객 객체

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    private Shipping shipping; // 배송 객체

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_order_id")
    private Map<String, OrderItem> orderItems = new HashMap<>(); // 주문 아이템 객체

    private BigDecimal grandTotal; // 주문 총 금액
}