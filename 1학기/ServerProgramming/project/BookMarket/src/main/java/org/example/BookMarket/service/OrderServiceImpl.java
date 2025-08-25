package org.example.BookMarket.service;

import org.example.BookMarket.domain.Book;
import org.example.BookMarket.domain.Order;
import org.example.BookMarket.repository.BookRepository;
import org.example.BookMarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void confirmOrder(String bookId, long quantity) { // 도서 재고 수 처리 메서드
        Book bookById = bookRepository.findByBookId(bookId);
        if (bookById.getUnitsInStock() < quantity) {
            throw new IllegalArgumentException("품절입니다. 사용 가능한 재고수:" + bookById.getUnitsInStock());
        }
        bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
    }

    public Long saveOrder(Order order) { // 주문 목록 저장 메서드
        Long orderId = orderRepository.saveOrder(order);
        return orderId;
    }
}
