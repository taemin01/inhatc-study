package org.example.BookMarket.service;

import org.example.BookMarket.domain.Order;

public interface OrderService {
    void confirmOrder(String bookId, long quantity);
    Long saveOrder(Order order);
}
