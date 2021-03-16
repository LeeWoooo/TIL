Session
===

HTTP 프로토콜은 계속 세션을 맺은 상태로 클라이언트와 서버가 연결돼 있는 것이 아니라 클라이언트가 서버에 요청할 때 세션이 맺어지고 서버에서 클라이언트로 응답을 보낸 이후 세션이 끊어진다.<br>

하지만 이렇게 세션이 매번 끊어져버린다면 예를 들어 웹 사이트에서 클라이언트가 로그인을 요청하고 서버가 해당 계정을 확인 한 뒤 로그인이 완료된 다음 페이지를 보여주더라도 계속해서 클라이언트가 로그인된 상태를 유지하기 어렵다.<br>

 **따라서 세션이 끊어지더라도 지속적으로 서버측에서 클라이언트에 대한 접속 정보를 가지고 있음으로 로그인을 유지할 수 있게 해주는 체가 JSP의 내장객체이자 자바의 HttpSession 객체인 session이다.**

## 쿠키와 세션의 차이점.

쿠키는 서버가 보내준 특정한 정보를 클라이언트 측에서 가지고 있는 방식이다. <br>

세션은 서버에서 클라이언트에 대한 정보를 가지고 있다.<br>

따라서 로그인과 같은 민감한 정보에 대해서는 쿠키를 사용하는 것 보다 세션을 사용하는 방식이 좋다. 또한 쿠키는 저장할 수 있는 데이터의 한계가 있는 반면 세션은 서버에 저장되기 대문에 저장할 수 있는 데이터의 한계가 쿠키보다 크다는 것이 장점이다.
<br>

세션의 정보는 계속 유지해줄 수도 있지만 대부분 30~60분 사이로 세션이 유지되는 최대기간을 조절해주는 경우가 많다. web.xml에서 지정할 수 있으며 코드로 확인해보자.

```xml
<session-config>
	<session-timeout>30</session-timeout>
</session-config>
```

<br>

session의 주요 method | 설명
:---: | :---:
void setAttribute(String name, Object value) | 파라미터 이름에 따른 객체 추가.
Object getAttribute(String name) | 파라미터 이름에 따른 객체 얻기
void removeAttribute(String name) | 파라미터 이름에 따른 객체 삭제
void invalidate() | 세션 무효화





<br>

코드에서 세션을 사용하는 방법을 로그인을 통해 알아보자.

1. login.jsp
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
			
			<% //먼저 세션이 있는지 확인을 해서 세션이 존재하면 loginOk로 리다이렉트
				if(session.getAttribute("memberId") != null){
					response.sendRedirect("loginOk.jsp");
				}
			%>
		
		
		
			<form action="loginCon" method="post">
				ID: <input type="text" name="mID"><br>
				PW: <input type="password" name="mPW"><br>
				<input type="submit" value="로그인">
			</form>
		</body>
</html>
```

<br>

2. LoginCon.java(servlet)
```java
@WebServlet("/loginCon")
public class LoginCon extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String mId = request.getParameter("mID");
		String mPw = request.getParameter("mPW");
		
		out.print("mId :" + mId);
		out.print("mPw :" + mPw);
		
		HttpSession session = request.getSession(); //세션은 request 객체로 붙어 얻을 수 있다.
		session.setAttribute("memberId", mId); //세션에 값을 추가한다.
		
		response.sendRedirect("loginOk.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

<br>

3. loginOk.jsp
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
			<%
				session = request.getSession();
				out.print("memberId : " + session.getAttribute("memberId") +"<br>");
			%>
			
			<form action="logout" method="post">
				<input type="submit" value="로그아웃">
			</form>
		</body>
</html>
```

<br>

4. LogoutCon.java (servlet)
```java
@WebServlet("/logout")
public class LogoutCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); //세션을 얻어온다.
		session.invalidate(); //로그아웃 버튼이 눌리면 세션을 무력화 한다.
		
		response.sendRedirect("login.jsp"); //로그인 페이지로 리다이렉트 한다.
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```