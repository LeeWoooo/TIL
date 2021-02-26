package com.sparta.week04.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //DB에 객체 mappingd을 하기 위해
@NoArgsConstructor //기본생성자
@Getter //getter lombok
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title; //상품명

    @Column(nullable = false)
    private String image; //이미지 url

    @Column(nullable = false)
    private String link; //상품 페이지 url

    @Column(nullable = false)
    private Integer lprice; // 최저가

    @Column(nullable = false)
    private Integer myPrice; // 사용자가 지정한 가격

    public Product(ProductRequestDTO productRequestDTO){
        this.title = productRequestDTO.getTitle();
        this.image = productRequestDTO.getImage();
        this.link = productRequestDTO.getLink();
        this.lprice = productRequestDTO.getLprice();
        this.myPrice = 0; //사용자가 만약 최저가 설정을 하지 않을 경우는 가장 작은값을 넣어주어 최저가가 붙지 않도록
    }

    public void update(ProductMyPriceRequestDTO productMyPriceRequestDTO){
        this.myPrice = productMyPriceRequestDTO.getMyPrice();
    }

    public void updateByItemDTO(ItemDTO itemDTO){
        this.lprice = itemDTO.getLprice();
    }

}
