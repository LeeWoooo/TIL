package com.sparta.week04.utils;

import com.sparta.week04.models.ItemDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component //스프링 부트 로그인될 때 Bean으로 등록해주세요~
public class NaverShopSearch {

    public String search(String query) {
        RestTemplate rest = new RestTemplate(); //RestTemplate 생성
        HttpHeaders headers = new HttpHeaders(); //headers 생성
        headers.add("X-Naver-Client-Id", "v7MX0sUJttnYIe7CzCzV"); //header에 값을 추가한다
        headers.add("X-Naver-Client-Secret", "TjhPmX3Dkm");
        headers.add("user-agent", "advanced-rest-client");
        headers.add("accept", "*/*");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers); //요청 request entity를 만들고
        //요청하는 url과 요청방식, 응답받는 자료형을설정
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode(); //통신에 대한 결과를 얻기 위해 생성
        int status = httpStatus.value();
        String response = responseEntity.getBody(); //요청으로 받은 http의 body를 얻는다.
//        System.out.println("Response status: " + status); //통신 상태를 정검
//        System.out.println(response); //api통신으로 얻은 결과를 출력
        return response;
    }

    public List<ItemDTO> fromJSONtoItems(String result){
        JSONObject rjson = new JSONObject(result); //매개변수로 문자열로 된 JSON을 입력하면 JSON Object로 만들어준다.
//        System.out.println(rjson);
        JSONArray items = rjson.getJSONArray("items"); //객체배열에서 내가 원하는 값을(JSON을 뽑아올 때) key값은 객체배열
//        System.out.println(items);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (int i=0; i<items.length(); i++) { //반복문을 돌리면서 객체배열에 있는 객체를 하나씩 꺼내온다.
            JSONObject itemJson = (JSONObject) items.get(i);
//            System.out.println(itemJson);
            //이후 json 객체에서 값을 가져올때는 get자료형("key이름")을 이용한다.
//            String title = itemJson.getString("title");
//            String link = itemJson.getString("link");
//            String image = itemJson.getString("image");
//            int lprice = itemJson.getInt("lprice");
//            System.out.println(title+link+image+lprice);
            ItemDTO itemDTO = new ItemDTO(itemJson);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

}
