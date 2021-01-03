Today I Learned (TIL)
===

## 2021-01-02~03
---

### 한 일


1. HTML 기초 강의 완강.

    * WEB의 기본적인 구조 및 Server,Client개념 이해.

    * HTML의 문법(body,head,form)에 대한 숙지 및 Tag 사용

2. JDBC Statement 사용 및 PreparedStatement사용

### 느낀점, 배운점

* 웹의 기본인 HTML을 사용하여 기본적인 웹페이지 형태를 만들어 보니 흥미로웠다.

    * 코드르 작성하는 대로 결과가 반영되어 직관적이고 HTML은 굉장히 배우기 쉬워서 금방금방 습득할 수 있었다.

    * HTML이 중요한 이유는 웹페이지의 뼈대를 만드는 일이고 뼈대를 만들면 그 이후 꾸미기는 기능만 사용하면 되기 때문.

        * CSS를 배워 웹페이지를 꾸며봅시다.

    * HTML 강의를 들었다고 HTML의 모든 내용을 들은 것은 아니기에 계속해서 추가하자.

* JDBC

    * java와 Oracle의 연결순서

        1. 드라이버 로딩
        2. Connection 얻기 (DriverManager)
        3. query 실행 객체를 Connection에서 얻기.(PreparedStatement, Statement)
        4. 바인드 변수를 사용했다면 바인드 변수에 값 할당.
        5. qeery문 실행 후 결과 얻기(executeQuery()를 사용했다면 ResultSet을 이용)
        6. 연결 끊기

### 내일 할일 

1. SIST강의 (JDBC)
2. 학원에 책 받으러 가기.
3. HTML을 이용하여 간단한 로그인 창 만들기