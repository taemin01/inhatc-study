package org.example.BookMarket.repository;

import java.util.HashMap;
import java.util.Map;

import org.example.BookMarket.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private Map<Long, Order> listOfOrders;
    private long nextOrderId;

    public OrderRepositoryImpl() { // 생성자
        listOfOrders = new HashMap<Long, Order>();
        nextOrderId = 2000;
    }

    public Long saveOrder(Order order) { // 주문 목록 저장 메서드
        order.setOrderId(getNextOrderId());
        listOfOrders.put(order.getOrderId(), order);
        return order.getOrderId();
    }

    private synchronized long getNextOrderId() { // 주문 아이디 생성 메서드
        return nextOrderId++;
    }
}
