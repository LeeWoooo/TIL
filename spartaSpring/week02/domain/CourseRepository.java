package com.sparta.week2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//Repository를 생성할 때는 interface로 생성을 한 후 JpaRepository를 상속 받고 테이블과 id의 데이터 형을 넣어준다.
public interface CourseRepository extends JpaRepository<Course, Long> {
    //CourseRepository sql의 역할을 대신해준다.
    //JpaRepository<Course, Long> 를 상속받는다.
    //Course는 table명 Primary key의 자료형이 Long이다.
}
