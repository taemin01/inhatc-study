package org.example.week5.controller;

import org.example.week5.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.week5.service.BookService;
import org.springframework.web.bind.WebDataBinder;
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

    @GetMapping("/add")
    public String requestAddBook() {
        return "addBook";
    }

    @PostMapping("/add")
    public String submitAddBook(@ModelAttribute Book book) {
        bookService.setNewBook(book);
        return "redirect:/books";
    }

    //ModelAttribute는 메서드 위에 쓰면 메서드 수준으로 요청 처리 전 먼저 호출된다
    //파라미터에 쓰면 요청 넘어온 이름과 커맨드 객체(도메인 객체)와 동일한 이름의 속성을 찾아 자동으로 매핑 시켜준다.
    @ModelAttribute //요청 처리 전에 호출되어서 model에 addTitle 속성을 추가한다.
    public void addAttributes(Model model) {
        model.addAttribute("addTitle", "신규 도서 등록 페이지");

    }

    //어던 필드만 바인딩할지 제한하는 코드이다. 여기서 description 빠지면 description 바인딩이 안된다.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("bookId", "name", "unitPrice",
                "author", "description", "publisher", "category", "unitInStock",
                        "totalPages", "releaseDate", "condition");
    }
}