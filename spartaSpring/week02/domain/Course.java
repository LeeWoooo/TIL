package com.sparta.week2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter //LomBok으로 인해 getter가 생성해준다.
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity // 테이블임을 나타냅니다. 데이터베이스 기준으로 table역할을 하는 class임을 선언
public class Course extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다. PK지정
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    @Column(nullable = false) //DB에서 NOT NULL 제약조건과 동일
    private String tutor;

    public Course(CourseRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.tutor = requestDto.getTutor();
    }

    public Course(String title, String tutor) {
        this.title = title;
        this.tutor = tutor;
    }

    //서비스
    public void update(CourseRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.tutor = requestDto.getTutor();
    }

    
}