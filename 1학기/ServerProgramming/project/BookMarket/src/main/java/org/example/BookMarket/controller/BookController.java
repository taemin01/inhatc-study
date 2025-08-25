package org.example.BookMarket.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.BookMarket.validator.UnitsInStockValidator;
import org.example.BookMarket.domain.Book;
import org.example.BookMarket.exception.BookIdException;
import org.example.BookMarket.exception.CategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.BookMarket.service.BookService;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.Map;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Value("${file.uploadDir}")
    private String fileDir;

    private final UnitsInStockValidator unitsInStockValidator;

    public BookController(UnitsInStockValidator unitsInStockValidator) {
        this.unitsInStockValidator = unitsInStockValidator;
    }

    @RequestMapping
    public String requestBookList(Model model) {
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
        if(list == null || list.isEmpty()) {
            throw new CategoryException();
        }
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
    public String requestAddBook(Model model) {
        //addBook.html에서 th:objec로 book을 사용하기 때문에
        //model에 book이라는 이름으로 객체를 넘겨줘야 한다.
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String submitAddBook(@Valid @ModelAttribute Book book, BindingResult result) {
        if(result.hasErrors()) {
            return "addBook";
        }
        MultipartFile bookImage = book.getBookImage();
        String saveName = bookImage.getOriginalFilename();
        File saveFile = new File(fileDir, saveName);
        if(bookImage != null && !bookImage.isEmpty()) {
            try {
                bookImage.transferTo(saveFile);
            } catch (Exception e) {
                throw new RuntimeException("파일 업로드 실패", e);
            }
        }
        book.setImageFileName(saveName);
        bookService.setNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/update")
    public String getUpdateBookForm(@ModelAttribute("updateBook") Book book,
                                      @RequestParam("id") String bookId, Model model) {
        Book bookById = bookService.findByBookId(bookId);
        model.addAttribute("book", bookById);
        return "updateForm";
    }

    @PostMapping("/update")
    public String processUpdatewBookForm(@ModelAttribute("updateBook") Book book) {
        MultipartFile bookImage = book.getBookImage();
        String rootDirectory = fileDir;
        if (bookImage!=null && !bookImage.isEmpty()) {
            try {
                String fname = bookImage.getOriginalFilename();
                bookImage.transferTo(new File(fileDir + fname));
                book.setImageFileName(fname);
            } catch (Exception e) {
                throw new RuntimeException("Book Image saving failed", e);

            }
        }
        bookService.setUpdateBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/delete")
    public String getDeleteBookForm(Model model, @RequestParam("id") String bookId) {
        bookService.setDeleteBook(bookId);
        return "redirect:/books";
    }

    @GetMapping("/download")
    public void downloadBookImage(@RequestParam("file") String paramKey, HttpServletResponse response)
        throws IOException {
        File imageFile = new File(fileDir + paramKey);
        
        response.setContentType("application/download");
        response.setContentLength((int)imageFile.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + paramKey + "\"");
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(imageFile);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
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
        //커스텀 Validator를 사용하기 위해서 @InitBinder를 사용한다.
        binder.addValidators(unitsInStockValidator);
        binder.setAllowedFields("bookId", "name", "unitPrice",
                "author", "description", "publisher", "category", "unitsInStock",
                        "totalPages", "releaseDate", "condition", "bookImage");
    }

    @ExceptionHandler(value = {BookIdException.class})
    public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidBookId", exception.getBookId());
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("errorBook");
        return mav;
    }
}