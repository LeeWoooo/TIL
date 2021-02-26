week04
===

## GOAL

1. Controller - Service - Repository에 대한 자신감 갖기.
2. 자바를 이용해 API를 이용하는 방법을 익힌다.
3. 스프링 스케줄러를 이용하여, 서버에게 원하는 작업을 원하는 시간에 시키는 방법을 익힌다.

<br>

---

<br>

## 백엔드 스프링 개발자에게 필요한 기본기란?

1. 3계층 숙달 (Controller - Service - Repository)

    개발의 핵심중 하나가 분업과 느슨한 결합이다. 각자가 맡은 바 책임을 다하면 기능이 온전히 작동하고, 느슨한 결합으로써 유연성과 확장성을 가지기 때문! 

    * Controller : 제일 바깥 쪽에서 요청을 받고, 응답을 되돌려주는 역할.
    * Service : 중간에서 구체적인 작업 순서를 결정
    * Repository : DB와 직접 소통함을 자료를 CRUD하는 역할
    
<br>

2. API의 handling

    앞에서 이야기한 느슨한 결합의 대표적인 예시가 API이다. 즉 API의 내부 로직을 알 필요가 없이 정해진 약속대로 요구하면 정해진 결과를 얻을 수 있게 되는 것이다! 또한 json으로 주고받는 data를 어떻게 자바로 요청을 하고 그결과를 다룰수 있는지 숙달이 필요하다. (ARC를 이용하여 API를 요청하고 응답을 자바코드로 변환을 익힐 예정!)

<br>

---

<br>

## 네이버 검색 API이용하기(쇼핑)

1. https://developers.naver.com/main/ 에 접속을 한다.

2. 원하는 API를 선택 후 API를 사용할 application을 등록해준다.

3. 발급받은 ClientID와 Client Secret를 이용하여 정해진 약속대로 요구하고 응답을 받는다. (제공해주는 예시를 토대로 사용해보자.)

* 예제

    <img src = https://user-images.githubusercontent.com/74294325/108602111-8faa3880-73e3-11eb-8d22-53f0570e3da5.JPG>

    요청하는 url은 이러하다. 실습에서는 json으로 응답을 받고 json으로 처리할 것이기 때문에 두번째 url을 이용하여 검색할 것이다.

    <br>

    호출예시는 이렇게 보여주고 있다.
    ```javascript
    curl "https://openapi.naver.com/v1/search/shop.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim" \
    -H "X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 client id 값}" \
    -H "X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 client secret 값}" -v
    ```

    <Br>

    여기서 crul이란 클라이언트에서 커맨드 라인이나 소스코드로 손 쉽게 웹 브라우저 처럼 활동할 수 있도록 해주는 기술을 의미하며 H는 http의 header를 의미한다. header에 X-Naver-Client-Id와 X-Naver-Client-Secret을 추가해준다.<br>

    요청은 query문자열을 이용하여 검색을 하게 되며 query문자열에 관한 정보는 api페이지를 확인하고 작성하면 될 것 같다. 여기서 검색하는 검색명이 한글일 경우 UTF-8로 인코딩해서 넘기는 것을 강제하는데 java에서는 어떻게 하는지 이후 확인해 볼 예정. <br>

    ARC로 검색한 결과를 확인해보면!

    <img src = https://user-images.githubusercontent.com/74294325/108602298-a1401000-73e4-11eb-99fa-2a36c8bb8eb9.JPG>

    <Br>

    <img src = https://user-images.githubusercontent.com/74294325/108602308-ab620e80-73e4-11eb-91d0-fc74c9815eb2.JPG>

    <Br>

    이와 같이 json으로 data를 받아올 수 있게 되는 것이다. **정리하자면 네이버의 검색 서버에 정해진 약속을 가지고 Data를 요청했고 정해진 약속을 통해 Data를 받아온 것이다.**

<br>

---

<br>

## Scheduler

스케줄링은 특정 기간동안 작업을 실행하는 프로세스이다. Spring Boot를 통해 Spring에서 지원하는 스케줄러를 간편하게 작성할 수 있다. <br>

### Schedule 기능 켜기

자바 설정(Java configuration) 관련 클래스에 @EnableScheduling 를 추가하면 기능을 사용할 수 있다.

```java
@EnableScheduling 
@EnableJpaAuditing
@SpringBootApplication
public class Week04PracApplication {
    public static void main(String[] args) {
        SpringApplication.run(Week04PracApplication.class, args);
    }
}
```

<br>

### 구현하기

@Scheduled annotation을 method에 선언하면 실행이 가능하며 @Scheduled를 포함하고 있는 class는 Bean으로 등록이 되어있어야 한다. 실행주기는 cron, fixedDelay, fixedRate 이 3가지 속성으로 지정을 할 수 있다. <br>

cron으로 실행 주기를 설정하는 방법은
```
총 6자리가 있다 
*           *           *           *           *           *
초(0~59)    분(0~59)  시간(0~23)    일(1~31)    월(1~12)    요일(0~7)
```

<br>

예제코드로 살펴보자면 아래의 코드는 AM1시마다 실행을 하여 네이버 쇼핑의 가격을 가져와서 DB에 저장되어 있는 상품의 최저가를 변경하는 코드이다.

```java
@Component
public class ProductScheduler {

    ...
    
    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException{
        System.out.println("상품 가격 업\데이트 시작");
        TimeUnit.SECONDS.sleep(1); //너무 짧은시간에 잦은 요청을 하게 되면 네이버에서 막아버리기 때문에 1초 쉬도록
        List<Product> productList = productRepository.findAll();
        for(Product product : productList){
            String title = product.getTitle();
            List<ItemDTO> itemDTOList = naverShopSearch.fromJsonItem(naverShopSearch.searchResult(title));
            ItemDTO itemDTO = itemDTOList.get(0);
            productService.updateAuto(product.getId(),itemDTO);
        }//end for
    }//updatePrice
}
```

<Br>

fixedDelay 는 이전 수행이 종료된 시점부터 delay 후에 재 호출되고 fixedRate 는 이전 수행이 시작된 시점부터 delay 후에 재 호출된다. 그러므로 fixedRate 로 지정 시 동시에 여러개가 돌 가능성이 존재한다

```java
@Scheduled(fixedDelay = 1000) //종료된 시점으로 1초 이후 재실행
@Scheduled(fixedRate = 1000) //실행된 시점으로 1초 이후 재실행
```

