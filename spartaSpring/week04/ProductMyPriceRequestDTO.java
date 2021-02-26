package com.sparta.week04.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductMyPriceRequestDTO {
    //사용자가 원하는 최저가를 입력했을 때 update하기위한 객체
    //myPrice
    private Integer myPrice;
}
