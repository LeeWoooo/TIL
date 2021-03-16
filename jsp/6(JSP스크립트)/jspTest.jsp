<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>
		<body>
		
			<%@ include file="header.jsp" %>
			<%--선언 태그 --%>
			<%!
				int num = 10;
				String str = "jsp";
				ArrayList<String> list = new ArrayList<String>();
				
				public void jspMethod(){
					System.out.println("jspMethod");	
				}
			%>
			<%--JSP 주석 태그 --%>
			
			<%--스크립트릿 태그 --%>
			<%
				if(num >0){
			%>
				<p>num > 0</p>
			<%
				}else{	
			%>		
				<p>num <= 0</p>
			<%
				}
			%>
			
			<%-- 표현식 태그 --%>
			<p>num의 값은 <%=num %>입니다.</p>
			
			<%@ include file="footer.jsp" %>
		</body>
</html>