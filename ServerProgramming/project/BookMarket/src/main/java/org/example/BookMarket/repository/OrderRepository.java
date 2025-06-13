package org.example.BookMarket.repository;

import org.example.BookMarket.domain.Order;

public interface OrderRepository {
    Long saveOrder(Order order);
}
