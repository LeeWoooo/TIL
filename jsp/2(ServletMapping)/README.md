Servlet Mapping
===

Servlet을 외부에서 요청하기 쉽도록 특정 문자를 이용해서 맵핑하는 방법을 말한다.

## Servlet Mapping란?

웹 컨테이너 안에 수많은 Servlet이 존재하고 있을 때 브라우저가 서블릿을 구분할 때 package경로와 Servlet의 이름이 들어간 full path를 사용하는 것이 아니라 mapping path를 이용하여 Servlet을 찾는 것을 쉽게해준다. (full path를 사용하게 되면 보안에 취약하고 URL이 복잡해진다.) <br>


그렇기에 Servlet Mapping를 사용하게 된다. 방법은 두가지가 있다. <br>

<br>

---

<br>

## web.xml(웹 환경설정 파일)파일을 이용한 mapping

* Project를 생성할 때 web.xml의 생성 옵션을 사용하여 Project를 생성한다.

* 생성한 Servlet을 web.xml에 등록하여 mapping을 하는 방법이다.

* 문법

    1. Servlet을 등록한다.

        <img src = https://user-images.githubusercontent.com/74294325/104312210-a5415e00-5519-11eb-8094-a5ed485bcc2f.JPG>

    등록을 할 떄 필요한 태그는 Servlet, Servlet-name, Servlet-class 이 필요하다. Servlet태그 안에서 Servlet-name태그를 사용하여 Servlet의 별칭을 지어준다. 사용자 임으로 작성해도 된다. 그 후 Servlet-class태그 안에서 생성된 Servlet의 path를 적어준다.

    2. Servlet을 mapping한다.

        <img src=https://user-images.githubusercontent.com/74294325/104312614-344e7600-551a-11eb-9d65-502cac9088d2.JPG>

    mapping 할 때 필요한 태그는 Servlet-mapping, Servlet-name, url-pattern 이 필요하다. Servlet-mapping 태그 안에서 Servlet-name 태그를 사용하여 mapping할 Servlet을 지정해 준 후 url-pattern 태그로 Servlet의 간단한 경로를 지정해준다.
    
<br>

---

<br>

## Annotation을 이용한 mapping

annotation을 이용하여 Servlet을 mapping하는 것은 훨씬 간단하다. 만들어진 Servlet위에 @WebServlet("/간단한경로의값")을 annotation으로 추가 해주면 끝이다. (Servlet을 생성하면서도 지정해줄 수 있다.)

* 방법

    <img src = https://user-images.githubusercontent.com/74294325/104313404-509ee280-551b-11eb-9891-96aeaaf2f4df.JPG>

<br>

---

<br>

하나의 Servlet에 여러개의 Servlet mapping을 할 수 있다. web.xml을 이용한 mapping과 annotation으로을 이용한 mapping의 경로의 값이 다르더라도 상관 없다.