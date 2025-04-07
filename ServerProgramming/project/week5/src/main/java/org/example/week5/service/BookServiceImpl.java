package org.example.week5.service;

import org.example.week5.domain.BookMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.week5.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookMarket> getAllBookMarketList() {
        return bookRepository.getAllBookMarketList();
    }
}
