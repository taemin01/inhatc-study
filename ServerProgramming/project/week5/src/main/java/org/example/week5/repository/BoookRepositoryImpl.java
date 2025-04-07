package org.example.week5.repository;

import org.example.week5.domain.BookMarket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoookRepositoryImpl implements BookRepository {
    private List<BookMarket> listOfBooks = new ArrayList<BookMarket>();

    public BoookRepositoryImpl() {
        BookMarket book1 = new BookMarket();
        book1.setBookId("ISBN1234");
        book1.setName("자바스크립트 입문");
        book1.setUnitPrice(30000);
        book1.setAuthor("조현영");
        book1.setDescription("자바스크립트 기초부터 심화까지 핵심 문법 학습 후 12가지 프로그램을 만들며 학습한 내용 확인 가능.");
        book1.setPublisher("길벗");
        book1.setCategory("IT전문서");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2024/02/20");

        BookMarket book2 = new BookMarket();
        book2.setBookId("ISBN1235");
        book2.setName("python 정석");
        book2.setUnitPrice(29800);
        book2.setAuthor("조용주, 임좌상");
        book2.setDescription("머신러닝, 사물인터넷, 데이터 분석 등 다양한 분야에 활용되는 직관적이고 간결한 문법의 파이썬 프로그래밍 언어를 최신 트렌드에 맞게 학습 가능");
        book2.setPublisher("길벗");
        book2.setCategory("IT교육교재");
        book2.setUnitsInStock(1000);
        book2.setReleaseDate("2023/01/10");

        BookMarket book3 = new BookMarket();
        book3.setBookId("ISBN1236");
        book3.setName("안드로이드 프로그래밍");
        book3.setUnitPrice(32000);
        book3.setAuthor("송미영");
        book3.setDescription("안드로이드 기본 개념을 체계적으로 익히고, 이를 실습 예제를 통해 익힙니다. 기본 개념과 사용법을 스스로 실전에 적용하는 방법을 학습한 다음 실습 예제와 응용 예제를 통해 실전 프로젝트 응용력을 키웁니다.");
        book3.setPublisher("길벗");
        book3.setCategory("IT교육교재");
        book3.setUnitsInStock(1000);
        book3.setReleaseDate("2023/06/30");
        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
    }

    @Override
    public List<BookMarket> getAllBookMarketList() {
        return listOfBooks;
    }
}
