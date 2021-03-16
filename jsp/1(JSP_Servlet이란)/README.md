Web Programing
===

## Web Programing이란?

* 서로서로 컴퓨터가 연결되어 있는 것을 네트워크라고 하며 연결되어 있는 상태에서 웹 브라우저를 통해 데이터를 주고 받는것을 인터넷 서비스라 한다.

* 사용자가 서버에게 데이터를 요청을 하면(Request) 서버는 사용자에게 요청한 데이터를 제공(Response)하는 과정을 Web Programing이라 한다.

    * 이 때 서버는 데이터를 가공하거나 조합해서 사용자에게 응답해준다.

    <img src = https://user-images.githubusercontent.com/74294325/104282839-f9851780-54f2-11eb-83c5-85e8db160678.JPG>

<br>

## 프로토콜(Protocol), IP

* 프로토콜 

    * 통신을 하기위한 규약으로 HTTP(글,택스트,이미지 등이 하이퍼링크를 통해 전송), FTP(파일전송), SMTP, POP 등이 있다.

* IP

* 어떠한 컴퓨터의 특정한 주소를 IP addr이라고 한다.

* DNS SERVER

    * 사용자가 입력한 도메인을 IP주소로 변환해주는 역할을 한다. (도메인과 IP를 맵핑하고 있다.)

* port

    * web server중 어느 곳으로 들어갈지를 명시해준다.

<br>

## JSP

JSP란 HTML 내부에 자바코드를 삽입하는 형식을 의미한다. 다시말해 서블릿의 단점을 보완하고자 만든 서블릿 기반의 스크립트 기술이다.<br>

서블릿을 이용하면 웹 프로그래밍을 할 수는 있지만 자바에 대한 지식이 필요하며 화면 인터페이스 구현에 너무 많은 코드를 필요로 하는 등 비효율적인 측면들이 있기 때문이다.

<br>

## Servlet

Servlet이란 서버에서 웹페이지 등을 동적으로 생성하거나 데이터 처리를 수행하기 위해 자바로 작성한 프로그램이다. WAS위에서 동작하는 자바프로그램이라 할 수 있으며 자바코드 안에 HTML태그가 삽입되어있다.<br>
Servlet의 확장자는 java로 되어있으며 client의 요청을 처리하고 그 결과를 다시 client에게 전송하는 자바프로그램을 의마한다.

<br>

## 웹 컨테이너 구조

개발자는 jsp파일 혹은 Servlet을 만든다. 그 후 사용자는 개발자가 제공한 것을 가지고 웹 컨테이너에 요청을 하게된다. 컨테이너는 안에서는 jsp파일 혹은 Servelt을 java->class->object순으로 변환되고 사용자에게 응답될 때는 HTML로 응답된다.(Servlet은 확장자가 .java이다)<br>
(개발자는 jsp파일만 작성하고 나머지는 웹 컨테이너가 작업을 하고 결과물을 사용자에게 HTML로 보여준다.)
<br>
이 과정을 전부 다 진행한 후 실행을 해 보면 HTML로 응답이 되고 응답된 소스파일을 확인하면 사용한 자바의 문법은 없어지고 HTML의 문법만 남아있다.

* JSP

    <img src = https://user-images.githubusercontent.com/74294325/104282324-37357080-54f2-11eb-9211-ad4ada8b84e5.JPG>

* servlet

    <img src = https://user-images.githubusercontent.com/74294325/104282358-474d5000-54f2-11eb-95ee-38cb00e6ad99.JPG>

## 참조
[인프런 강의(실전 JSP (renew ver.) - 신입 프로그래머를 위한 강좌)](https://www.inflearn.com/course/%EC%8B%A4%EC%A0%84-jsp_renew/questions?page=1&limit=15)