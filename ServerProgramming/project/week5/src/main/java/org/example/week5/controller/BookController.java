package org.example.week5.controller;

import org.example.week5.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.week5.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.Map;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping
    public String requestBookList(Model model) {
        System.out.println("### requestBookList 호출됨");  // 로그 확인용
        List<Book> list = bookService.getAllBookMarketList();
        model.addAttribute("bookList", list); //데이터 넘기는 부분 쓰지 않으면 데이터를 동적으로 할당할 수 없음
        return "books";
    }

    @RequestMapping("/all")
    public ModelAndView requestAllBookList() {
        ModelAndView mav = new ModelAndView();
        List<Book> list = bookService.getAllBookMarketList();
        mav.addObject("bookList", list);
        mav.setViewName("books");
        return mav;
    }

    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model) {
        Book bookById = bookService.findByBookId(bookId);
        model.addAttribute("book", bookById);
        return "book";
    }

    @GetMapping("/{category}")
    public String requestBookByCategory(@PathVariable("category") String category, Model model) {
        List<Book> list = bookService.findByCategory(category);
        model.addAttribute("bookList", list);
        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBookByFilter(@MatrixVariable(pathVar = "bookFilter")Map<String, List<String>> bookFilter, Model model) {
        Set<Book> booksByFilter = bookService.findByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";
    }
}