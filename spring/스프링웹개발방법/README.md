스프링 웹 개발
===

spring으로 웹개발에는 3가지 방법이 있다.

1. 정적인 컨텐츠
2. 웹 MVC + 템플릿 엔진
3. API

---

<br>

## 정적인 컨텐츠

정적인 컨텐츠는 간단하게 이야기하자면 HTML을 있는 그대로 보여주는 구조이다. HTML을 작성을 했다면 이 후 HTML에는 프로그래밍을 하여 동적으로 변화를 준다거나 할 수 없고 작성된 그대로 보여주게 된다. 정적인 컨텐츠에 요청을 하는 방법은 URL에 html명.html과 같이 입력을 하면 정적인 컨텐츠에 접근을 할 수 있게 된다. 컨텐츠 파일의 위치는 resources 밑에 static 디렉토리에 존재한다.

<br>

* 결과 

    <img src = https://user-images.githubusercontent.com/74294325/108139380-5338bd00-7103-11eb-9ff1-d6d9d28b909c.JPG>

<br>

이와같이 static 안에있는 컨텐츠들은 GET방식으로 직접 입력해서 접근하여 결과를 확인할 수 있다. 구조를 확인해보자면 

<br>

* 구조 

    <img src = https://user-images.githubusercontent.com/74294325/108139898-60a27700-7104-11eb-8868-27bc757f060d.JPG>

<br>

요청이 들어오면 **1차적으로 요청과 관련된 controller를 찾는다. controller에서 우선순위를 갖는다.** 이 후 controller가 없을 경우 static영역에서 해당 html을 찾아 응답을 해주게 된다.

<br>

---

<br>

## MVC + 템플릿 엔진

## MVC

먼저 MVC 패턴을 간단히 이야기 해보자면 디자인 패턴중 하나로 하나의 웹 애플리케이션을 만들때 그 구성요소를 세가지로 나누는 것을 이야기한다. 

1. Model
2. View
3. Controller

위와 같이 3가지로 나누게 된다. 여기서 View는 화면을 보여주는 일에만 최선을 다하여야 하고 Model,Controller는 비지니스 로직이나 내부적인 처리를 하는데에 최선을 다해야 한다. <br>

주로 Controller에서 비지니스 로직 등을 처리하고 처리한 Data를 Model에 담아서 View에 넘겨주면 넘겨받은 데이터를 이용해 View가 화면을 그리게 되는 구조이다. (추 후 더 자세히 MVC에 대한 공부를 할 예정입니다.) <br>

그럼 코드로 확인해보자.

```java
@Controller
public class TestModelController {

    @GetMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("data","컨트롤러에서 넘어온 값입니다.");
        return "testModel";
    }

}
```

<br>

현재 data라는 key에 "컨트롤러에서 넘어온 값입니다."라는 값이 담겨서 retrun되는 testModel view에 넘어가게 된다. 이렇게 되면 testModel에서는 값을 받을 준비를 해 넘어온 값을 받아오면된다. 현재 실습에서는 템플릿엔진을 thymleaf를 이용하였습니다. <br>

```html
<body>
<p th:text="'Model객체 test입니다. ---' + ${data} + '---'" >Model 객체를 thymeleaf를 이용하여 test합니다.</p>
</body>
```

* 결과

    <img src = https://user-images.githubusercontent.com/74294325/108141831-d3612180-7107-11eb-8fa1-ec1ee56c53d1.JPG>


<br>

* 구조

    <img src = https://user-images.githubusercontent.com/74294325/108142128-803b9e80-7108-11eb-8b46-6c5b83df5cee.JPG>

<br>

브라우저가 controller에 지정된 mapping주소를 입력해 요청을 하게 되면 controller에서 로직들을 처리하고 view에 보낼 data가 있다면 Model 객체에 저장하여 View에 넘겨줍니다. **이 때 controller에서 응답할 page의 이름을 문자열로 return하게 되는데 이에 해당하는 view를 viewresolver가 찾아준다.** 이 때 viewname mapping을 하게되는데 구조는 이러하다.

```
resources:templates+{viewName(controller에서 return된 문자열)}.html
```

또한 넘어온 data는 템플릿 엔진에 의해 처리되고 랜더링이 끝나면 해당 view를 응답하게 된다.

<br>

---

<br>

## API

MVC는 mapping된 HTML을 넘겨주는 것이라면 API는 데이터(주로 body)를 전달해주는 방식이다. 주로 ajax통신을 할 때 사용되거나 서버와 서버끼리의 통신에서 사용이 된다. <br>

이때에는 controller method위에 @ResponseBody 라는 annotation을 이용하게 되는데 mvc방식과는 다르게 body에 해당하는 data만 view에 넘겨줄 수 있게 된다. **이 때에 body는 HTML의 body부가 아닌 HTTP의 body이다.**<br>

대부분 객체를 JSON 형식으로 view에 넘겨준다. (String(문자열)을 넘겨줄 수도 있다.)<br>

코드로 확인해보자.

```java
//controller
@GetMapping("/api/test")
@ResponseBody
public Person apiTest(){
    return  new Person("이우길",26);
}

//person
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

<br>

* 결과

    <img src = https://user-images.githubusercontent.com/74294325/108143567-5a63c900-710b-11eb-8970-ef57cdc2ca6d.JPG >

<br>

이와같이 @ResponseBody를 사용한 controller에서 return값으로 객체를 주게되면 json형태의 데이터로 응답을 해줄 수 있다. (설정을 하게되면 xml으로도 응답이 가능합니다.) <br>

* 구조

    <img src = https://user-images.githubusercontent.com/74294325/108144251-a400e380-710c-11eb-9190-8d5cdf530c6b.JPG>

<br>

controller에서 return되는 타입이 객체인지 문자열인지에 따라 Convertor가 다르게 작동을 하게 된다. 객체일 경우는 "MappingJackson2HTTPMessageConvertor"에서 처리를 하여 JSON을 응답하게 되고 문자열일 경우 "StringHTTPMessageConvertor"에서 처리하여 문자열을 반환하게 된다. 

<br>

JSON으로 처리해주는 라이브러리는 대표적으로 2개가 있는데 Jackson과 Gson이 있다. spring에선 Jackson을 사용하고 있다.<br>

<br>

### @RestController

스프링 프레임워크 4.x 버전이상부터 사용가능한 annotation으로 @Controller와 @ResponseBody가 결합된 annotation입니다. controller class에 @RestController를 붙이면 하위 method에서 @ResponseBody를 붙이지 않아도 문자열과 JSON을 전달할 수 있다. 위의 예제코드에서 controller class의 annotation을 @RestController로 변경해서 결과를 확인해보면.

```java
@RestController
public class TestRestController {

    @GetMapping("api/testRestController")
    public Person apiTest(){
        return  new Person("이우길",26);
    }
}
```

<br>

* 결과

    <img src = https://user-images.githubusercontent.com/74294325/108145143-4f5e6800-710e-11eb-9431-856bdddc9ab5.JPG>

<Br>

---

<br>

## 참조 

스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)