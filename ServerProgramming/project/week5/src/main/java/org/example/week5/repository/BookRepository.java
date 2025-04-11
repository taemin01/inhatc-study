package org.example.week5.repository;

import org.example.week5.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookRepository {
    List<Book> getAllBookMarketList();
    List<Book> findByCategory(String category);
    Book findByBookId(String bookId);
    Set<Book> findByFilter(Map<String, List<String>> filter);

}
