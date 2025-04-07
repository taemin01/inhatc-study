package org.example.week5.controller;

import org.example.week5.domain.BookMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.week5.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/BookMarket", method = RequestMethod.GET)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String requestBookList(Model model) {
        System.out.println("### requestBookList 호출됨");  // 로그 확인용
        List<BookMarket> list = bookService.getAllBookMarketList();
        model.addAttribute("bookList", list);
        return "books";
    }
}