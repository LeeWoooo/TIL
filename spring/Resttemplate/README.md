RestTemplate 사용법
===

테스트 API호출 샘플로 Naver의 쇼핑목록을 검색해서 가져와보겠습니다.

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

    이와 같이 json으로 data를 받아올 수 있게 되는 것이다. **정리하자면 네이버의 검색 서버에 정해진 약속을 가지고 Data를 요청했고 정해진 약속을 통해 Data를 받아온 것이다.** <br>

    이것을 자바에서 처리를 하고자 한다면 RestTemplate과 Http를 이용해야 한다. 하지만 편리한 세상속에서 살고 있는 우리로써는 ARC에서 코드까지 전부 다 제공을 해주는 것을 볼 수 있다.

    * ARC의 코드 제공

        <img src = https://user-images.githubusercontent.com/74294325/108680688-83cc8c80-7531-11eb-9d0a-9e6ac5caebcf.JPG>


    <br>

    제공은 해주지만 모르고 사용할 수는 없기 간닪하게 정의가 무엇인지 살펴보자면

<br>

## RestTemplate이란?

spring 3.0부터 지원을 하며 스프링에서 제공하는 http 통신에 유용하게 사용할 수 있는 template이며 http 서버와 통신을 단순화 하고 RESTful 원칙을 지킨다. <BR>

특징으로는 다음과 같다.

1. 기계적이고 반복적인 코드를 최대한 줄여준다.
2. RestFul형식에 맞춘다.
3. json으로 응답을 쉽게 받을 수 있다.

