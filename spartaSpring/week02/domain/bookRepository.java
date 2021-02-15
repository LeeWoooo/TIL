package com.sparta.week2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<book,Long> {

}
