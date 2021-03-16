JSP 내장 객체
===

## Goal

* request, response 외 JSP에서 기본적으로 제공하는 객체에 대한 이해


<br>

## out (javax.servlet.jsp.jspWriter)

* 생성 : client 요청시

* 소멸 : 요청에 대한 처리 완료 시

* 사용 범위 : 요청을 처리하기 위한 하나의 스레드

<br>

클라이언트로 데이터를 전송하는 출력 스트림이다. 출력되는 내용들은 모두 **버퍼에 담았다가 한번에 보내게 된다.** 서블릿의 FileWriter 객체와 동일한 역할을 가지는 내장 객체이고, 뷰(View)의 역할을 하는 JSP답게 서블릿에서는 만들어줘야 하는 출력스트림을 내장 객체로 가지고 있어서 바로 사용할 수 있다.<br>

HTML에서는 body에 그냥 텍스트를 입력하면 되지만 스크립트릿<% %> 안에서 HTML코드를 섞지 않고 바로 출력할 수 있게 해준다.<br>

물론 서블릿으로 변환되면 HTML코드도 모두 out객체를 통해 전송되고, 기본적으로 일단 버퍼에 담았다가 한번에 출력하는 버퍼 스트림이다.

<br>

out의 자주 사용하는 method | 설명
:---: | :---:
void println() | 클라이언트에게 출력(HTML 소스에 개행문자 적용)
void print() | 클라이언트에게 출력(개행문자 없음)
int getBufferSize() | 출력버퍼의 전체 크기를 리턴
int getRemaining() | 출력버퍼에서 현재 남아있는 크기를 리턴
void clearBuffer() | 버퍼에 쌓인 내용을 모두 삭제(전송하지 않음)
void close() | 출력 버퍼에 저장된 내용을 전송하고 스트림을 닫는다.

<br>

코드를 통해 확인해보자.

```jsp
<%
	out.println("줄바꿈이 일어나는 출력method");
	out.println("안녕하세요");
%>

<br>

<%
	out.print("줄바꿈이 일어나지 않는 출력method");
	out.print("안녕하세요");
%>
```

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EX</title>
	</head>
	<body>

	줄바꿈이 일어나는 출력method
	안녕하세요

	<br>

	줄바꿈이 일어나지 않는 출력method안녕하세요

</html>
```

<br>

**println()은 개행문자를 포함하고 있는method이지만 클라이언트에게 응답된 View에는 줄바꿈이 일어나지 않는다.** 그 이유는 개행문자가 HTML 코드에 적용된 것이지 응답되는 HTML에 적용되는것이 아니기 때문이다.

<br>

---

<br>

## page

page는 JSP페이지 그 자체를 가리키는 객체이다. 자바class에서 this와 비슷하며 자바class에서 this는 주로 자기 자신이 가지고 있는 값과 다른 객체가 가지고 온값을 명시적으로 구분할 때 사용된다. 그와 비슷한 역할을 하는 것이 page이다.

<br>

---

<br>

## config (javax.servletConfig 객체)

* 생성/ 소멸 : JSP,서블릿의 생명주기와 동일하다.(하지만 초기화 값은 컨테이너의 생명주기와 동일)

* 사용범위 : 매핑된 JSP, 서블릿 내(받아서 재전달하는 경우 제외)

<br>

ServletConfig 객체는 서블릿이 구동될 때 web.xml에서 읽어온 초기화 정보를 가지고 있는 객체이다. **따라서 config객체는 개발자가 미리 서블릿 또는 JSP에 매핑해둔 초기화 정보를 저장하고 있는 내장 객체이다.** <br>

서블릿 객체가 생성될 때 자동으로 생성되며, 같은 서블릿의 객체는 동일한 config 객체를 가지게 된다. 초기화 정보는 web.xml에 기술되어 있고 컨테이너가 생성될 때 한 번 읽어서 ServletConfig 객체에 저장해두면 컨테이너를 재시작 하지 않는 이상 바꾸지 않기 때문에 **config 객체의 정보 또한 web.xml파일을 수정하고 컨테이너를 재가동하지 않는 이상 변할 수 없다.**<br>

서블릿에 변동이 생겨 삭제되고 로드될 때 다시 생성되기는 하지만 web.xml의 값을 바꿀 수 없는 만큼 초기화 값은 변하지 않는다고 보면 된다.
<br>

config의 자주사용하는 method | 설명
:---: | :---:
String getInitParameter(String name) | param-name의 param-value 값을 가져옴
Enumberation&lt;String&gt; getInitParameterNames() | 모든 param-name 값을 가져옴
String getServletName() | web.xml에서 명시한 &lt;servlet-name&gt;의 이름을 가져옴
ServletContext getServletContext() | ServletContext 객체를 반환 (application 객체와 동일)

<br>

사용법을 코드와 함께 확인하자.

```xml
 <servlet>
  	<servlet-name>servletEx</servlet-name>
  	<jsp-file>/jspEx.jsp</jsp-file>
  	<init-param>
  		<param-name>adminId</param-name>
  		<param-value>admin</param-value>
  	</init-param>
  	<init-param>
  		<param-name>adminPw</param-name>
  		<param-value>1234</param-value>
  	</init-param>
  </servlet>
  <servlet-mapping>
  	<servlet-name>servletEx</servlet-name>
  	<url-pattern>/jspEx.jsp</url-pattern>
  </servlet-mapping>
```

<br>

위와 같이 매핑을 하면서 설정을 하며 param-name에 name을 설정하고 param-value에 값을 설정하여 맵핑한 서블릿이나 jsp에서 가져다 사용하면 된다.

```jsp
<%
	adminId = config.getInitParameter("adminId");
	adminPw = config.getInitParameter("adminPw");
%>
```

<br>

## application (javax.servlet.ServletContext 객체)

* 생성 / 소멸 : 컨테이너의 생명주기와 동일

* 사용범위 : 프로젝트 내 모든 객체에서 접근 가능

<br>

어플리케이션은 내외부의 여러 환경 정보를 담고 있는 객체이다. **처음 컨테이너가 구동될 때 단 하나의 객체만 생성된다.** 기본적으로 서버에 대한 정보나 서블릿에 대한 정보들을 가지고 있다. **어플리케이션(프로젝트 단위) 내 모든 JSP/servlet들이 공유하는 객체이다. 데이터를 편리하게 운송하는 수단으로 주로 사용된다.**

application method | 설명
:---: | :---:
void setAttribute(String name, Object o) | application 객체에 추가 객체를 저장
Object getAttribute(String name) | application 객체에 추가된 객체를 가져옴
void removeAttribute(String name) | application 객체에 특정 Attribute를 지움
String getServerInfo() | 컨테이너 이름과 버전을 리턴
void clearBuffer() | 버퍼에 쌓인 내용을 모두 삭제(전송하지 않음)
void log(String msg) | 제공된 문자열을 서블릿 로그 파일에 기록

<br>

config와 비슷하게 web.xml에서 초기화 값을 지정해줄 수도 있다. 대신 특정 JSP나 servlet에 맵핑하지 않고 모두가 쓸 수 있는 초기화 파라미터이다. <br>

코드로 확인해보자.

```xml
<context-param>
      <param-name>location</param-name>
      <param-value>/img/src/</param-value>
</context-param>
<context-param>
      <param-name>dataName</param-name>
      <param-value>leewoo.jpg</param-value>
</context-param>
```

```jsp

<%!
	String location;
	String dataName;
	String addr;
%>

<%
	location = application.getInitParameter("location");
	dataName = application.getInitParameter("dataName");

	System.out.println(location +" / " + dataName);

	//값 추가 하기
	application.setAttribute("addr","seoul");

	//값 가져오기
	addr = (String)application.getAttribute("addr"); 
%>

```

<br>

---

<br>

## exception (java.lang.Throwable 객체)

JSP 페이지에서 예외가 발생하였을 때 처리해줄 수 있는 객체이다. 기본적으로 영어 메세지가 가득한 톰캑의 예외 발생 화면 말고 개발자가 예외발생 페이지를 만들어 제공할 수 있다.

<br>

page 지시자를 이용하여 예외처리 페이지를 등록해서 사용한다. **예외가 발생하는 시점에서 넘겨줄 페이지를 지정하는 작업을 말한다.**

```jsp
<%@ page errorpage ="jspException.jsp" %>
```

<br>

예외를 처리하는 페이지에서도 **필수적으로 지시자를 통한 예외 페이지를 설정해주어야 한다.**  예외 페이지에는 response.setStatus(200);을 설정하게 되는데 이 페이지가 정상임을 알려주기 위한 설정이다.

<br>

서버에서 예외를 처리하기 위한 페이지를 예외가 발생된 페이지로 잘못 인식하는 경우를 방지하기 위함이다.

```jsp
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
```

<br>


* web.xml에서 지정하는 방법

초기화 설정값에서 예외 페이지를 설정해준다. 예외 코드에 따라 서로 다른 페이지로 이동시킬 수 있으며 이 방법에서는 호출하는 쪽에서는 지시자를 통해 예외 페이지를 설정할 필요가 없지만 예외 페이지는 위와 같이 page 지시자와 setStatus() 메소드를 설정해줘야 한다.

```xml
<error-page>
	<error-code>500</error-code>
	<location>/exceptionPage500.jsp</location>
</error-page>
	
<error-page>
	<error-code>404</error-code>
	<location>/exceptionPage404.jsp</location>
</error-page>
```

<br>

exception 객체는 지시자, web.xml, 어노테이션을 통해 이 페이지가 예외를 처리하기 위한 페이지임을 명시해야만 사용할 수 있다.<br>

exception의 자주사용하는 method | 설명
:---: | :---:
String getMessage() | 발생한 예외의 메세지 리턴
String toString() | 발생한 예외 클래스 및 예외 메세지 리턴
void printStackTrace() | 발생한 예외 역추적을 위한 예외 스트림 출력 (서버에 출력)
void printStackTrace(PrintWriter s) | 발생한 예외 역추적을 위한 예외 스트림 출력 (클라이언트에게 출력)

<br>

코드로 확인해보자.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8">

<%@ page errorPage = "exception.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex</title>
	</head>
	<body>
		
		<%!
			String str;
		%>
		<%
			str.toString();
		%>
</html>
```

```jsp
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%response.setStatus(200);%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%!
			String exceptionMsg;
			String str;
		%>

		<%
			exceptionMsg = exception.getMessage();
			str = exception.toString();
		%>

		예외가 발생하였습니다. <br>
		예외 내용: <%=exceptionMsg%> <br>
		예외 class 및 내용 : <%=str%>

	</body>
</html>
```