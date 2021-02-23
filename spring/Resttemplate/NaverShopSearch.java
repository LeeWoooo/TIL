package com.sparta.week04_prac.util;

import com.sparta.week04_prac.model.ItemDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {

    public String searchResult(String query){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "v7MX0sUJttnYIe7CzCzV"); //부여받은 API key
        headers.add("X-Naver-Client-Secret", "TjhPmX3Dkm"); //부여받은 API key
        headers.add("user-agent", "advanced-rest-client");
        headers.add("accept", "*/*");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        //매개변수로 받은 query를 이용하여 네이버에서 제공하는 api사용 get방식으로 상품정보를 얻어온다.
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
        //HttpStatus httpStatus = responseEntity.getStatusCode();
        //int status = httpStatus.value();
        String response = responseEntity.getBody();
        //System.out.println("Response status: " + status); 상태 출력
        //System.out.println(response); 넘어온 값 확인 하기 위한 출력
        return response;
    }//searchResult

    public List<ItemDTO> fromJsonItem(String response) {
        //전달 받은 문자열 형태의 json을 json 객채로 변경을 한다.
        JSONObject searchResult = new JSONObject(response);
        //그 중에 내가 필요한 item 속성값만 얻어온다.
        JSONArray searchItems = searchResult.getJSONArray("items");

        //item 값을 얻어온 것을 가지고 itemDTO를 생성하여 반환해준다.
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(int i = 0; i<searchItems.length(); i++){
           JSONObject searchItem = searchItems.getJSONObject(i);
           ItemDTO itemDTO = new ItemDTO(searchItem);
           itemDTOList.add(itemDTO);
        }//end for

        return itemDTOList;
    }
}
