package com.sparta.week04.service;

import com.sparta.week04.models.ItemDTO;
import com.sparta.week04.models.Product;
import com.sparta.week04.models.ProductMyPriceRequestDTO;
import com.sparta.week04.repository.ProductRepository;
import com.sparta.week04.models.ProductRequestDTO;
import com.sparta.week04.utils.NaverShopSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final NaverShopSearch naverShopSearch;

    @Autowired
    public ProductService(ProductRepository productRepository, NaverShopSearch naverShopSearch) {
        this.productRepository = productRepository;
        this.naverShopSearch = naverShopSearch;
    }

    //관심상품에 등록되어 있는 상품리시트 전부 불러오기
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findOne(Long id){
        return productRepository.findById(id).orElseThrow(
            ()->new NullPointerException("해당 아이디의 상품이 존재하지 않습니다.")
        );
    }

    //관심상품에 상품 등록하기
    public Product createProduct(ProductRequestDTO productRequestDTO){
        return productRepository.save(new Product(productRequestDTO));
    }

    //상품에 최저가 등록하기
    @Transactional
    public Long update(Long id, ProductMyPriceRequestDTO productMyPriceRequestDTO){
        Product updateProduct =  productRepository.findById(id).orElseThrow(
                ()->new NullPointerException("해당 아이디의 상품이 존재하지 않습니다.")
        );
        updateProduct.update(productMyPriceRequestDTO);
        return id;
    }

    //최신화
    @Transactional
    public Long updateBySearch(Long id, ItemDTO itemDTO){
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 아이다의 상품이 존재하지 않습니다.")
        );
        product.updateByItemDTO(itemDTO);
        return id;
    }


    //네이버에 검색을 해서 상품들 가져오기
    public List<ItemDTO> getAllItem(String result){
        return naverShopSearch.fromJSONtoItems(naverShopSearch.search(result));
    }


}
