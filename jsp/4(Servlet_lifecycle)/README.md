Servlet Life-Cycle
===

## GOAL

* 사용자의 요청에 의해서 생성된 Servlet의 생명주기 (생성, 실행, 종료)에 대한 이해

    1. Servlet 생명주기
    2. 생명주기 관련 method

<br>

---

<br>

## Servlet 생명주기

* 생명주기

    <img src = https://user-images.githubusercontent.com/74294325/104319955-002c8280-5525-11eb-9f0b-8f7727b89baa.JPG>

<br>

0. @PostConstruct의 Annotation이 지정된 method가 실행된다.

1. 요청이 오면 Servlet class가 로딩이 되어 요청에 대한 Servlet 객체가 생성된다.

2. 웹 컨테이너는 init() method를 호출해서 Servlet을 초기화 한다.

3. service() method를 호출해서 Servlet이 브라우저의 요청을 처리하도록 한다. -> service()가 doGet(), doPost()를 호출한다.

4. 웹 컨테이너는 destroy를 호출하여 Servlet을 제거한다.

5. @PreDestroy의 Annotation이 지정된 method가 실행된다.


<br>

---

<br>

## Servlet 생명주기 test

```java
@WebServlet("/tsc")
public class TestServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("---doget()---");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@PostConstruct
	public void funPc() {
		System.out.println("---@PostConstruct---");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("---init()---");
	}
	
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("---service()---");
		super.service(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("---destroy()---");
	}
	
	@PreDestroy
	public void funPd() {
		System.out.println("---@PreDestroy---");
	}

}
```

* 실행 결과

    <img src = https://user-images.githubusercontent.com/74294325/104319915-edb24900-5524-11eb-8d5f-ea9f530bc28d.JPG>

    * 먼저 @Postconstruct로 지정한 method가 실행 된 후 init() -> service() -> doget() 순으로 호출 된다. ( service() method를 선언하지 않아도 doget()이 호출된다. )

    * 이 상태로 새로고침을 하게되면 init()을 또 다시 호출하는 것이 아닌 service() -> doget()순으로 호출된다.

<br>

---

<br>

주로 init()에는 DB와 연결할 때 필요한 준비작업들을 선언하게 되며 destroy에서는 DB의 자원을 끊어주는 작업들을 선언해준다.

<br>

---

<br>

[참조 blog victolee](https://victorydntmd.tistory.com/154)