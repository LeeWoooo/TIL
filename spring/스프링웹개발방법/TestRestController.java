package com.syudy.springhello.controller;

import com.syudy.springhello.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("api/testRestController")
    public Person apiTest(){
        return  new Person("이우길",26);
    }
}
