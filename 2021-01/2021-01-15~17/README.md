Today I Learned (TIL)
===

## 2021-01-15~17
---

### 한 일

* Java_LiveStudy

    * Exception

        JDK 1.7 이상부터 try-with-resources를 사용할 수 있게 되었다.
        주로 리소스를 사용하고 자원을 반납하게 되는 경우 이전까지는 try-catch-finally문에서 fianlly에서 자원을 반납하게 되었지만 try-with-resources문을 사용하면 finally문에서 반납하던 자원을 try-with-resources문 에서 잡아준다. <br>

        Autoclosable을 구현한 리소스만 try-with-resources문을 사용할 수 있다.

* JSP,Servlet

    * JSP의 request와 response의 사용

        JSP의 request는 Servlet과 동일하게 사용된다. 주로 form태그의 값들을 jsp로 받아오게 되는데 사용되는 method는 getparameter()이고 넘어오는 값이 배열일 경우(주로 checkBox) getparameterValues()로 받아 사용한다. <br>

        response의 경우 사용자의 페이지를 리다이렉트 시킬 때 주로 사용되며 사용되는 method는 sendRedirect(url)을 주로 사용하게 된다.

    * Jsp의 경우 자바의 변수나 method를 선언할 때는 <%! %>와 같은 선언 태그를 사용하며 단지 자바의 문법을 사용할 때에는 <% %>를 사용하게 된다.

### 내일 할일 

* SIST강의 

* 인프런 (jsp,servlet강의 듣기)
