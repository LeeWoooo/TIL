Servlet 데이터 공유
===

## servlet parameter

서블릿 파라미터는 **서블릿이 초기화 될때 파라미터를 만들어지고** getInitParameter()를 이용하여 가져와서 사용한다. jsp의 config와 사용법은 동일하다 <br>

코드로 확인해보자.

```xml
<servlet>
  	<servlet-name>servletEx</servlet-name>
  	<servlet-class>com.servlet.servletEx</servlet-class>
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
  	<url-pattern>/se</url-pattern>
  </servlet-mapping>
```

<br>

이와같이 mapping 할 때 공유되는 파라미터 값들을 지정해줄 수 있다. 위의 저장한 값을 servlet에서 사용하는 코드이다.

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String adminid = getServletConfig().getInitParameter("adminId");
	String adminpw = getServletConfig().getInitParameter("adminPw");
		
	PrintWriter out = response.getWriter();
	out.print("<p> adminid:"+ adminid+"</p>");
	out.print("<p> adminpw:"+ adminpw+"</p>");
}
```

<br>

이와 같이 먼저 config를 얻은 후 jsp와 동일하게 **getInitParameter("매개변수명"); 으로 가져다가 사용할 수 있다.**

<br>

---

<br>

## context parameter

context parameter 또한 동일하게 web.xml에서 **프로젝트 내 서블릿에서 공유되는 값을 지정해 놓고 가져다가 사용할 수 있도록 한다.**<br>

코드로 확인해보자

```xml
<context-param>
  	<param-name>imgDir</param-name>
  	<param-value>/upload/img</param-value>
</context-param>
<context-param>
  	<param-name>testServerIP</param-name>
  	<param-value>127.0.0.1</param-value>
</context-param>
```

<br>

이 또한 name와 value가 한 쌍으로 이루어지며 servlet에서 가져다 사용하는 코드를 확인해보자.

```java
String imgDir = getServletContext().getInitParameter("imgDir");
String testServerIP = getServletContext().getInitParameter("testServerIP");
		
out.print("<p> imgDir:"+ imgDir+"</p>");
out.print("<p> testServerIP:"+ testServerIP+"</p>");
```
<br>

서블릿에서 사용할 때는 jsp와 다르게 application을 통해 얻어오는 것이 아니라 getServletContext()와 getInitParameter를 가져와서 사용한다.

<br>

값을 추가하는 것 또한 getServletContext()과 setAttribute()를 이용해서 사용한다. 추가를 하게 되면 프로젝트 내 모든 서블릿에서 가져다가 사용할 수 있다. <br>

코드로 확인해보자.

```java
getServletContext().setAttribute(이름,값);

//ex
getServletContext().setAttribute("name","leewoo");
```

<br>

값을 가져오는것 동일하다. getServletContext()과 getAttribute()를 이용해서 사용한다. 가져오면 Object형태로 가져오기 때문에 String로 casting해준다.<br>

```java
getServletContext().getAttribute("가져올 값의 이름");

//ex
String name = (String)getServletContext().getAttribute("name");