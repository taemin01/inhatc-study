package org.example.BookMarket.repository;

import org.example.BookMarket.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookRepository {
    List<Book> getAllBookMarketList();
    List<Book> findByCategory(String category);
    Book findByBookId(String bookId);
    Set<Book> findByFilter(Map<String, List<String>> filter); //인터페이스로 선언을 하고 구현체를 넣는 방식으로 사용하는 것이 유지보수에 좋다.
    void setNewBook(Book book);

}
