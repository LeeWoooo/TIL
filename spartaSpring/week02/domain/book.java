package com.sparta.week2.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity // 이 class는 테이블 역할 입니다.
public class book {

    @Id//Primary key 역할을 합니다.
    @GeneratedValue(strategy = GenerationType.AUTO) //sequence역할과 비슷한 mysql에서 자동 증가값
    private Long bookId;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookPublisher;

    @Column(nullable = false)
    private Long bookPrice;


    public String getBookName() {
        return bookName;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public long getBookPrice() {
        return bookPrice;
    }

    public book(String bookName, String bookPublisher, long bookPrice) {
        this.bookName = bookName;
        this.bookPublisher = bookPublisher;
        this.bookPrice = bookPrice;
    }
}
