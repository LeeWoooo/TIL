package com.syudy.springhello.controller;

import com.syudy.springhello.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestModelController {

    @GetMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("data","컨트롤러에서 넘어온 값입니다.");
        return "testModel";
    }

    @GetMapping("/api/test")
    @ResponseBody
    public Person apiTest(){
        return  new Person("이우길",26);
    }


}
