Servlet Request, Servlet Response
===

## GOAL

* 사용자의 요청과 web-sever의 응답을 담당하는 객체에 대해 학습.

    1. HttpServlet
    2. HttpServletRequest
    3. HttpServletResponse

<br>

---

<br>

## HttpServlet (Abstract Class)

<br>

* 상속 구조 

    <img src = https://user-images.githubusercontent.com/74294325/104316004-24856080-551f-11eb-8fd5-0b8802a47e86.JPG>

<br>

* HttpServlet는 웹서버와 통신을 하기 위한 객체를 구현하고 상속받고 있기 때문에 우리가 만드는 Servlet은 HttpServlet만 상속받아 작성하면 된다.

* HttpServlet 클래스에서 사용자 요청을 처리하는 doGet/doPost 메서드는 모두 HttpServletRequest와 
HttpServletResponse 객체를 매개변수로 가지고 있다.

* HttpServletRequest와 HttpServletResponse 객체는 서블릿과 클라이언트 사이를 연결해주는 중요한 객체들 이다.

    ```java
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    ```

<br>

---

<br>

## 요청과 응답 과정

<br>

### WAS가 웹브라우져로부터 Servlet요청을 받으면 요청을 받을 때

* 전달 받은 정보를 HttpServletRequest객체를 생성하여 저장
* 웹브라우져에게 응답을 돌려줄 HttpServletResponse객체를 생성(빈 객체)
* 생성된 HttpServletRequest(정보가 저장된)와 HttpServletResponse(비어 있는)를 Servlet에게 전달

<br>

### HttpServletRequest
* Http프로토콜의 request 정보를 서블릿에게 전달하기 위한 목적으로 사용
* Header정보, Parameter, Cookie, URI, URL 등의 정보를 읽어들이는 메소드를 가진 클래스
* Body의 Stream을 읽어들이는 메소드를 가지고 있음

<br>

### HttpServletResponse
* Servlet은 HttpServletResponse객체에 Content Type, 응답코드, 응답 메시지등을 담아서 전송함

<br>

---

<br>

## 주요 method

<img src = https://user-images.githubusercontent.com/74294325/104318832-4bde2c80-5523-11eb-8538-4b46dd14b120.JPG>

<br>

---

[참조 woody._.k blog](https://woojong92.tistory.com/entry/Servlet%EA%B5%AC%EC%A1%B0%EC%99%80-HttpServlet-%ED%81%B4%EB%9E%98%EC%8A%A4)