package com.sparta.week04.controller;

import com.sparta.week04.models.ItemDTO;
import com.sparta.week04.models.ProductMyPriceRequestDTO;
import com.sparta.week04.models.ProductRequestDTO;
import com.sparta.week04.service.ProductService;
import com.sparta.week04.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //현재는 전부 객체나 문자열을 반환할것이기 때문에 RestController를 사용해도 된다.
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("/api/products")
    @ResponseBody
    public Product createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.createProduct(productRequestDTO);
    }

    @PutMapping("api/products/{targetId}")
    @ResponseBody
    public Long updateMyPrice(@PathVariable Long targetId,@RequestBody ProductMyPriceRequestDTO ProductMyPriceRequestDTO){
        return productService.update(targetId,ProductMyPriceRequestDTO);
    }

}
