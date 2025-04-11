package org.example.week5.service;

import org.example.week5.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.week5.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBookMarketList() {
        return bookRepository.getAllBookMarketList();
    }

    @Override
    public Book findByBookId(String bookId) {
        return bookRepository.findByBookId(bookId);
    }

    @Override
    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    @Override
    public Set<Book> findByFilter(Map<String, List<String>> filter) {
        return bookRepository.findByFilter(filter);
    }
}
