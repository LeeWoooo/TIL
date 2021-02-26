package com.sparta.week04.controller;

import com.sparta.week04.models.ItemDTO;
import com.sparta.week04.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchRestController {

    private final ProductService productService;

    @Autowired
    public SearchRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("api/search")
    @ResponseBody
    public List<ItemDTO> getItemAll(@RequestParam String query){ //넘어오는 query문자열에서 값을 가져온다. ->@RequestParam
//        System.out.println(productService.getAllItem(query));
        return productService.getAllItem(query);
    }
}
