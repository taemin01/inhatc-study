package org.example.BookMarket.service;

import org.example.BookMarket.domain.Book;


import java.util.List;
import java.util.Map;
import java.util.Set;


public interface BookService {
    List<Book> getAllBookMarketList();
    Book findByBookId(String bookId);
    List<Book> findByCategory(String category);
    Set<Book> findByFilter(Map<String, List<String>> filter);
    void setNewBook(Book book);
    void setUpdateBook(Book book);
    void setDeleteBook(String bookID);
}