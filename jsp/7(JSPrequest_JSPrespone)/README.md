JSP request, JSP response
===

## Goal

* 사용자의 요청과 web-server의 응답을 담당하는 객체에 대해서 학습합니다.



<br>

## request

JSP의 request와 JSP response는 Servlet과 동일하게 사용된다. 사용되는 method는 동일하다.<br>

예제코드를 작성하면서 확인해보자.

<br>

* html의 form 형식

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="mSignUp.jsp" method="get">
		name : <input type ="text" name="m_name"><br>
		password : <input type ="password" name="m_pass"><br>
		hobby :  
				sport<input type="checkbox" name="m_hobby" value="sport">,
				cook<input type="checkbox" name="m_hobby" value="cook">.
				travel<input type="checkbox" name="m_hobby" value="travel"><br>
		
		<input type="submit" value="전송하기">
		</form>
</body>
</html>
```

<br>

* JSP에서 데이터 받기

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>
		<body>
			<%!
				String m_name;
				String m_pass;
				String[] m_hobby;
			%>
			
			<%
				m_name=request.getParameter("m_name");
				m_pass=request.getParameter("m_name");
				m_hobby=request.getParameterValues("m_hobby");
			%>
			
			m_name = <%=m_name%> <br>
			m_pass = <%=m_pass%> <br>
			m_hobby = 
			<%
				for(String hobby : m_hobby){
			%>
				<%=hobby %>
			<%
				}
			%><br>
		</body>
</html>
```

<br>

이처럼 Servlet과 동일하게 getParameter를 사용하여 Aragument로 받은 요소의 값을 가져와서 사용하게 된다. <br>

가져오는 값이 배열일 경우(주로 CheckBox)는 method를 getParameterValues로 받아 배열에 담아준다.<br>

## response

response는 client의 응답에 관련된 작업을 핸들링 할 수 있는 method를 소유하고 있는 내장객체이다. <br>

이 객체는 HttpServletResponse class의 객체로 client에게 데이터를 전송하기 위한 output Stream을 얻어낼 수도 있으며 client의 쿠키 사용이나 redirect와 같은 작업을 할 수 있는 class이다.<br>

response를 주로 사용할 때는 client의 페이지를 리다이렉트 시킬 때 사용된다. sendRedirect()를 사용하며 예제로 확인해보자.

* firstPage

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>
		<body>
			<h1>First Page</h1>
			<%
				response.sendRedirect("secondPage.jsp");
			%>
		</body>
</html>
```

이 페이지가 호출이 되면 response.sendRedirect("secondPage.jsp")로 인해 지정한 jsp로 이동하게 되어 있다.
