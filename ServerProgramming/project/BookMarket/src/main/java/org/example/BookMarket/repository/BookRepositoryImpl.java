package org.example.BookMarket.repository;

import org.example.BookMarket.domain.Book;
import org.example.BookMarket.exception.BookIdException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        book1.setBookId("ISBN1234");
        book1.setName("자바스크립트 입문");
        book1.setUnitPrice(BigDecimal.valueOf(30000));
        book1.setAuthor("조현영");
        book1.setDescription("자바스크립트 기초부터 심화까지 핵심 문법 학습 후 12가지 프로그램을 만들며 학습한 내용 확인 가능.");
        book1.setPublisher("길벗");
        book1.setCategory("IT전문서");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2024/02/20");
        book1.setImageFileName("ISBN1234.png");

        Book book2 = new Book();
        book2.setBookId("ISBN1235");
        book2.setName("python 정석");
        book2.setUnitPrice(BigDecimal.valueOf(29800));
        book2.setAuthor("조용주, 임좌상");
        book2.setDescription("머신러닝, 사물인터넷, 데이터 분석 등 다양한 분야에 활용되는 직관적이고 간결한 문법의 파이썬 프로그래밍 언어를 최신 트렌드에 맞게 학습 가능");
        book2.setPublisher("길벗");
        book2.setCategory("IT교육교재");
        book2.setUnitsInStock(1000);
        book2.setReleaseDate("2023/01/10");
        book2.setImageFileName("ISBN1235.png");

        Book book3 = new Book();
        book3.setBookId("ISBN1236");
        book3.setName("안드로이드 프로그래밍");
        book3.setUnitPrice(BigDecimal.valueOf(32000));
        book3.setAuthor("송미영");
        book3.setDescription("안드로이드 기본 개념을 체계적으로 익히고, 이를 실습 예제를 통해 익힙니다. 기본 개념과 사용법을 스스로 실전에 적용하는 방법을 학습한 다음 실습 예제와 응용 예제를 통해 실전 프로젝트 응용력을 키웁니다.");
        book3.setPublisher("길벗");
        book3.setCategory("IT교육교재");
        book3.setUnitsInStock(1000);
        book3.setReleaseDate("2023/06/30");
        book3.setImageFileName("ISBN1236.png");
        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
//        this.bookServiceImpl = bookServiceImpl;
    }

    @Override
    public List<Book> getAllBookMarketList() {
        return listOfBooks;
    }

    @Override
    public Book findByBookId(String bookId) {
        Book bookInfo = null;
        for(int i = 0; i < listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if(book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
                bookInfo = book;
                break;
            }
        }

        if(bookInfo == null) {
            throw new BookIdException(bookId);

        }
        return bookInfo;
    }

    @Override
    public List<Book> findByCategory(String category) {
        List<Book> bookByCategory = new ArrayList<Book>();

        for(int i = 0; i < listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if(book != null && book.getCategory() != null && book.getCategory().equals(category)) {
                bookByCategory.add(book);
            }
        }
        return bookByCategory;
    }

    @Override
    public Set<Book> findByFilter(Map<String, List<String>> filter) { //출판사와 카테고리로 필터링 하는 메소드
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<String> bookByFilter = filter.keySet();
        if(bookByFilter.contains("publisher")) {
            for(int j = 0; j < filter.get("publisher").size(); j++) {
                String publisherName = filter.get("publisher").get(j);
                for (Book book : listOfBooks) {
                    if (book != null && book.getPublisher() != null && book.getPublisher().equals(publisherName)) {
                        booksByPublisher.add(book);
                    }
                }
            }
        }
        if(bookByFilter.contains("category")) {
            for(int i = 0; i < filter.get("category").size(); i++) {
                String categoryName = filter.get("category").get(i);
                List<Book> bookList = findByCategory(categoryName);
                booksByCategory.addAll(bookList);
            }
        }
        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    @Override
    public void setNewBook(Book book) {
        if(book != null) {
            listOfBooks.add(book);
        } else {
            throw new IllegalArgumentException("도서 정보가 없습니다.");
        }
    }
}
