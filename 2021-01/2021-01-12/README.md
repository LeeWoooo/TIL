Today I Learned (TIL)
===

## 2021-01-12
---

### 한 일

* SIST강의 (HTML5)

    * 기본적인 CSS의 사용(HTML에서 어떻게 CSS를 사용하는지, 선택자, 서식)

* 노마드 코더 바닐라JS 2일차

    * JS의 자료형 : 주로 자바와 비슷하다. String, Number, float, boolean,,,s

    * Array : 데이터들을 정렬할 때 사용하며 모든 자료형이 요소로 들어갈 수 있으며 자바와 달리 자료형이 다른 값 또한 입력할 수 있다.
    
    * Object : 자바의 class와 비슷하다 생각하면 쉬울 수 있을 것 같다. 주로 프로퍼티와 method로 구성되어 있다.

* JSP/Servlet

    * JSP와 Servlet의 기본적인 개념을 다시 적립

    * Servlet의 작성법

        * HttpServlet을 상속받아 사용하게 되며 method로는 기본적으로 doget(), dopost()를 갖게된다.

        * 생명주기로는 intit() -> service() 이 때 doget(), dopost()가 불리게 된다. -> destroy()

        * HTML의 form 태그에서 지정한 method의 속성값 대로 Servlet안에서 처리하게 된다.

            * getparameter() : String
            * getParameterValues() : String[]
            * getParameterNames() : Enumeration

* 외부 project import하기

    * 외부 project를 가지고 와서 build path를 현재 자바 설정과 동일하게 설정해준다. ( tomcat 또한 마찬가지 )

    * Target runtime Apache Tomcat 오류처리를 하는데 조금 걸렸던것 같다.. properties에서 targer runtime을 설정해주면 된다.(현재 설치되어 있는 tomcat의 버전으로 맞춰준다.)

### 내일 할일 

1. SIST (CSS)

2. 노마드 코더 바닐라JS 3일차

3. 인프런으로 익힌 Servlet을 가지고 기본적인 회원가입 만들어보기
