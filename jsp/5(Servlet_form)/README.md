Servlet from처리
===

## Goal

* 사용자의 form 데이터를 Servlet에서 처리하는 방법에 대해서 이해.

1. form 태그

2. doGet

3. doPost

<br>

---

<br>

## Form 태그

### POST

* **header의 body에 담겨서 전송이된다.**(URL에 사용자가 전송한 Data가 표시되지 않는다.)

* URL상에 전달한 정보가 표시되지 않는다.

* GET에 비해서 보안상 약간의 우위에 있다(사실상 동등,값이 직관적으로 노출되지 않기 때문)

* **전송할 수 있는 데이터의 길이 제한이 없다.**

* 크롬 개발자 도구 Network메뉴에서 사용자가 입력한 Data를 알 수 있다.

### GET

* **URL에 정보가 담겨서 전송된다.**(사용자가 전송한 Data가 URL상에 표현된다. ?로 Parameter를 구분한다.)

* 전송할 수 있는 정보의 길이가 제한되어 있다.(URL의 길이는 무한하지 않기 때문에 제한되는 길이를 넘을 수 없다. 256자 이하)

* 퍼머링크로 사용될 수 있다.(퍼머링크란 어떠한 정보를 식별하는 고유한 주소체계를 의미한다.)

### POST,GET의 사용

* GET방식은 북마크와 같은 기능,정보를 가져올 때 정보에 접근하는 URL로 사용

* POST방식은 SERVER쪽에 어떠한 작업을 수행할 때 사용(DATA를 기록하거나 수정할 때)

* 예를 들어 id를 넘겨서 게시판의 리스트를 가져온다면 GET방식을 사용할 것이고 글을 작성한다고 하면 POST를 쓰는 것이 일반적이다.

* **즉 GET은 가져오는 것이고 POST는 수행하는 것이다**

    * get은 SELECT적인 성향을 가지고 있다. **get은 서버의 어떠한 데이터를 가져와서 보여주는 용도로 대부분 쓰이지 서버의 값이나 상태등을 바꾸지 않는다.**<br>
    (게시판의 리스트라던지 글보기 기능 등등,,,)

    * POST는 **서버의 값이나 상태를 바꾸기 위해서 사용한다.**



<br>

---

<br>


## Servlet에서 사용자가 입력한 값 가져오기

HttpServletRequest class에서 form태그로 전송된 데이터를 받아오는데 method를 사용하여 받아낸다. 주로 사용 되는 method는 3가지 정도가 있다.
<br>

* getParameter(String name) : form control의 name의 값을 알고 있을 때, name에 대한 value 값을 받아온다. 반환형은 String이다.

* getParameterValues(String name) : form control의 name의 값을 알고 있을 때, 같은 name에 대해 여러개의 값을 얻을 때 사용한다. 반환형은 String[]이다.

* getParameterNames() : form 태그에서 데이터가 넘어올 때 넘어온 form control의 name의 값을 알 지 못할 때 사용한다. 반환형은 Enumeration이다.

	* Enumeration : Collection 프레임워크가 만들어기 전, Iterator의 이전 버전이다.

		* hasMoreElements() : 읽어올 요소가 남아있는지 확인. 있으면 true, 없으면 false. Iterator의 hasNext()와 같음
		* nextElement() : 다음 요소를 읽어 옴. Iterator의 next()와 같음

<br>


<br>

## 예제 코드

### HTML

---

```html
<form action="mSignUp" method="get">
		name : <input type="text" name="m_name"> </br>
		password : <input type="password" name="m_pass"></br>
		gender :  Man<input type="radio" name="m_gender" value="M" checked="checked">, 
					Woman<input type="radio" name="m_gender" value="W"></br>
		hobby : Sport<input type="checkbox" name="m_hobby" value="sport">, 
		Cooking<input type="checkbox" name="m_hobby" value="cooking">, 
		Reading<input type="checkbox" name="m_hobby" value="reading">,
		Travel<input type="checkbox" name="m_hobby" value="travel"></br>
		residence : <select name="m_residence">
						<option value="seoul" selected="selected">Seoul</option>
						<option value="gyeonggi">Gyeonggi</option>
						<option value="chungcheong">Chungcheong</option>
						<option value="jeonra">Jeonra</option>
						<option value="jeju">Jeju</option>
						<option value="gyeongsang">Gyeongsang</option>
						<option value="gangwon">Gangwon</option>
					</select></br>
					<input type="submit" value="sign up">
	</form>
```

<form action="mSignUp" method="get">
		name : <input type="text" name="m_name"> </br>
		password : <input type="password" name="m_pass"></br>
		gender :  Man<input type="radio" name="m_gender" value="M" checked="checked">, 
					Woman<input type="radio" name="m_gender" value="W"></br>
		hobby : Sport<input type="checkbox" name="m_hobby" value="sport">, 
		Cooking<input type="checkbox" name="m_hobby" value="cooking">, 
		Reading<input type="checkbox" name="m_hobby" value="reading">,
		Travel<input type="checkbox" name="m_hobby" value="travel"></br>
		residence : <select name="m_residence">
						<option value="seoul" selected="selected">Seoul</option>
						<option value="gyeonggi">Gyeonggi</option>
						<option value="chungcheong">Chungcheong</option>
						<option value="jeonra">Jeonra</option>
						<option value="jeju">Jeju</option>
						<option value="gyeongsang">Gyeongsang</option>
						<option value="gangwon">Gangwon</option>
					</select></br>
					<input type="submit" value="sign up">
	</form>

<br>

---

<br>

### Servlet

---

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(" -- doGet() -- ");
		
		String m_name = request.getParameter("m_name");
		String m_pass = request.getParameter("m_pass");
		String m_gender = request.getParameter("m_gender");
		String[] m_hobbys = request.getParameterValues("m_hobby");
		String m_residence = request.getParameter("m_residence");
		
		System.out.println("m_name : " + m_name);
		System.out.println("m_pass : " + m_pass);
		System.out.println("m_gender : " + m_gender);
		System.out.println("m_hobbys : " + Arrays.toString(m_hobbys));
		System.out.println("m_residence : " + m_residence);
		
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) { //몇개가 넘어왔는지 알 수 없으니 while문
			String name = (String) names.nextElement();
			System.out.println("name : " + name);
		}
		
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	System.out.println(" -- doPost() -- ");
		
	doGet(request, response);
	
}

/*
결과
m_name : leewoogil
m_pass : 1234
m_gender : M
m_hobbys : [sport, travel]
m_residence : seoul
name : m_name
name : m_pass
name : m_gender
name : m_hobby
name : m_residence
*/

```

<br>

**코드를 보면 servlet에서 doget에 code를 몰아 넣고 dopost에는 doget을 호출해서 받아온 request와 response를 넘겨주었다. 이렇게 코드 구성을 한 이유는 servlet에 요청이 오면 request와 response이 생성되서 들어올텐데 호출되는 method만 다를 뿐 들어오는 객체는 동일하기 때문에 유지보수, 복잡도 면에서 훨씬 좋은 코드가 될 수 있다.**