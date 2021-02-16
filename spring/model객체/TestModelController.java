package com.syudy.springhello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestModelController {

    @GetMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("data","컨트롤러에서 넘어온 값입니다.");
        return "testModel";
    }

}
