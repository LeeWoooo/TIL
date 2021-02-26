package com.sparta.week04.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    //창이 2개가 있는데 처음에는 사용자가 저장을 눌렀을 때 저장되는 Product
    //title link image lprice
    private String title;
    private String link;
    private String image;
    private Integer lprice;
}
