한글처리
===

2byte의 언어가 브라우저에서 정상적으로 깨지지않고 보이게 하기 위해 사용하는 방법이다.<br>

get방식과 post방식으로 나뉘는데 post방식부터 확인해보자.<br>

post방식으로 처리할 떄는 post방식에 request.setCharcterEncoding("UTF-8");을 추가하면된다. 또한 응답을 해주는 ContentType 또한 지정해줘야한다.<br>

코드로 확인해보자.


1. 서블릿
```java
@WebServlet("/mSignUp")
public class MSignUp extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
		//브라우저로부터 들어오는 characterEncoding을 UTF-8로 설정 (jsp에서 사용하는 charset에 마춰야함) 
		response.setContentType("text/html; charset=UTF-8");
		//브라우저로 응답하는 charset을 UTF-8로 설정 이또한 응답을 받는 브라우저와 charset 동일하게 마춰야함.
		
		String name = request.getParameter("m_name");
		String nickNmae = request.getParameter("m_nickname");
		
		PrintWriter out = response.getWriter();
		
		out.print("<p>"+name+"</p>");
		out.print("<p>"+nickNmae+"</p>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

<br>

2. jsp

```jsp
<% request.setCharacterEncoding("UTF-8");%> <!--jsp도 동일하게 인코딩 charset을 요청하는 브라우저와 동일하게 마춰준다.-->
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
				String name = "";
				String nick ="";
			%>
			
			<%
				name = request.getParameter("m_name");
				nick = request.getParameter("m_nickname");
			%>
			
			이름:<%=name %> <br>
			별명:<%=nick %>
			
		</body>
</html>
```

<br>

get방식은 server.xml에서 설정해줄 수 있다. connector에 URIEncoding="UTF-8"을 추가해준다. xml을 수정하고 이클립스에 있는 서버와 톰캣의 서버를 동기화 시켜준다.

```java
<Connector URIEncoding="UTF-8" connectionTimeout="20000" port="8090" protocol="HTTP/1.1" redirectPort="8443"/>
```

<br>

다음으로 filter의 설정을 알아보자. **filter를 생성하면 사용자가 요청하고 서버가 응답하는 가운데 filter를 끼고 연결을 하게 되는데 요청하는 것과 응답되는 객체가 filter를 통해 인코딩하게 된다.**<br>

filter는 따로 생성을 해줘야하는데 FIlter라는 interface를 구현해야한다. <br>

코드로 확인해보자.

```java
public class tempFilter implements Filter{

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//필터가 생성되서 초기화 될 때 사용
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//request filter
		
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response); //chain.doFilter를 기준으로 위에가 request 아래가 response
		
		//response filter
		
	}
	
	@Override
	public void destroy() {
		//필터가 종료될 때 (소멸될 때) 사용
	}
}
```

<br>

**filter는 한글 인코딩 뿐만 아니라 브라우저의 요청이 들어올 때, 서버의 응답이 브라우저로 갈 때 그 객체들을 가공할 수 있는 방법이다.**

<br>

이렇게 filter를 생성했으면 web.xml에서 mapping를 해주면 모든 요청이 들어오고 응답이 나갈때 따로 인코딩을 지정해 줄 필요가 없다.

```xml
  <filter>
  	<filter-name>tempFilter</filter-name>
  	<filter-class>com.filter.tempFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>tempFilter</filter-name>
	<url-pattern>/*</url-pattern>  
  </filter-mapping>
```
