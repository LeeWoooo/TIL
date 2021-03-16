쿠키
===

쿠키는 우리가 일상 생활에서 먹는 쿠키와 동일하다. 과자를 먹다보면 흘리며 흔적을 남기게 되는데 이와 동일한 개념으로 프로그램에서 흔적을 남긴다는 의미를 가지고 있다. 
더 구체적으로 이야기 하자면 **서버와 클라이언트가 연결을 시도했던 흔적을 남겼다가 추 후 다시 연결할 때 흔적을 참조하여 연결하게된다.** <br>

http프로토콜은 **클라이언트가 요청을 하고 서버가 응답을 하게되면 연결이 끊어지는 특징을 가지고 있다.** 그 이유는 하나의 서버에 수 많은 클라이언트가 연결 될 수 있기 때문이다.
**따라서 다시 연결이 되는 시점은 클라이언트가 다시 요청을 하는 시기이다.**<br>

간단히 예를 들어 로그인을 요청해서 서버로 로그인 성공응답을 받고 연결이 끊어진 상태로 페이지 이동이 되면 로그인은 유지가 되지 않을 것이다. 이럴 때를 대비하여 쿠키를 사용하는 것이다.<br>

**쿠키를 이용하여 클라이언트와 서버의 데이터를 연결해주는 역할을 한며 쿠키는 클라이언트의 기존 연결정보에 저장을 하게 된다.**<br>

## 정리하자면!

클라이언트 측에 정보를 저장해두고 서버에서 해당 값을 수정/변경/삭제 할 수 있는 기능을 가진 객체를 쿠키라고 한다. 보안에 취약하기 때문에 민감한 정보와 관련 없는 정보들에 대해 주로 사용<br>

쿠키는 브라우저에 저장이 되기 대문에 서로 다른 브라우저는 다른 쿠키를 가지고 있고 서로 정보를 교환할 수 없다.

<br>

## 브라우저로부터 쿠키 정보 가져오기

사용자의 요청이 담긴 request객체에서 모든 쿠키 정보를 얻어온다.

| request 객체의 쿠키 관련 method | 설명 |
| :---: | :---: |
Cookie[] getCookies() | 사용자 브라우저가 가진 모든 쿠키 객체를 배열로 반환 |

```java
<%
	Cookie[] cookie = request.getCookies();
%>
```

<br>

## 클라이언트에게 쿠키 전달

클라이언트에게 최종 응답해주는 객체인 response에는 쿠키를 add할 수 있는 method가 준비되어 있다. 생성한 쿠키를 모두 response 객체에 담아주면 최종 회신할 때 쿠키 객체가 브라우저로 전달돼서 저장된다. <br>

**주의할 점은 생성할 때 뿐만 아니라 기존 쿠키의 값을 변경하거나 유효시간을 0으로 설정해 쿠키를 만료시키고 나서도 꼭 response객체에 다시 담아줘야 한다.** <br>

| response 객체의 쿠키 관련 method | 설명 |
| :---: | :---: |
void addCookie(Cookie cookie) | response 객체에 쿠키를 첨부함 |

```java
<%
	// 생성하고 담아줌
	Cookie idCookie = new Cookie("id", "leewoo");
	Cookie nameCookie = new Cookie("name", "servlet");
	response.addCookie(idCookie);
	response.addCookie(nameCookie);
		
	// 수정해도 다시 담아줘야 함
	idCookie.setMaxAge(0);		// 무효화
	nameCookie.setValue("JSP");	// 값 주정
	response.addCookie(idCookie);
	response.addCookie(nameCookie);
%>
```
<br>

---

<br>

코드에서 쿠키를 사용하는 방법을 로그인을 통해 알아보자.

1. 서블릿에서 먼저 쿠키가 null인지 확인을 한다.(이 때 쿠키가 존재하지 않는다면 쿠키를 생성한다.)

	* **쿠키 안에는 여러개의 정보가 있을 수 있기 때문에 배열로 관리한다.**

	* **쿠키는 서버가 아닌 클라이언트에게 저장이 되니 request 객체로 부터 얻는다.**

```java

String mId = request.getParameter("mID");
String mPw = request.getParameter("mPW");

Cookie[] cookies = request.getCookies(); //쿠키를 얻어온다.
Cookie cookie = null;
		
	for(Cookie c : cookies) { //쿠키를 배열에 넣어 쿠키의 이름과 값을 확인하면서 내가 원하는 쿠키가 있는지 확인
		System.out.println("c.getName" + c.getName() +" /  c.getValue: "+ c.getValue()); 
			
		if(c.getName().equals("meberId")) { //만약 내가 원하는 쿠기가 있다면 쿠키에 넣어주고
			cookie = c;
		}
	}
		
	if(cookie == null) {// 만약 내가 원하는 쿠키가 없다면 처음 접속한 것이기 때문에 쿠키를 만들어서 사용자가 입력한 값을 넣어준다.
		System.out.println("cookie is null");
		cookie = new Cookie("meberId", mId);
	}

```

<br>

2. 생성한 쿠키를 클라이언트에게 추가해준다.

```java
response.addCookie(cookie);
```

<br>

3. 쿠키의 생명주기를 설정해준다.
```java
cookie.setMaxAge(60*60); 
//현재 설정한 값은 60초*60이니 1시간이다.
//쿠키는 한시간 동안 유요하며 그 중간에 또 다시 연결이 되면 초기화 된다.
```

<br>

4. 이 후 페이지 이동을 시켜주거나 해서 처리하면 된다.
```java
response.sendRedirect("loginOk.jsp");
```
<br>

5. 처음 데이터를 요청하여 서블릿에서 보낸 페이지에서 쿠키를 검사한다.
```jsp
<%//로그인을 하기전에 쿠키가 있는지 확인을 한 후 쿠키가 있다면 로그인 폼을 보여주는 것이 아니라 로그인Ok라는 jsp로 바로 이동시킨다.
Cookie[] cookies = request.getCookies();
System.out.println("cookies :" + cookies);
			
if(cookies != null){
	for(Cookie c : cookies){
		if(c.getName().equals("meberId")){
			response.sendRedirect("loginOk.jsp");
		}
	}
}
%>
<form action="loginCon" method="post">
	ID : <input type="text" name="mID">
	PWD : <input type="password" name="mPW">
	<input type="submit" value="login">
</form>
```

<br>

