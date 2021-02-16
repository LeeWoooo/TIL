model 객체
===

controller의 method를 작성할 때 특별하게 Model이라는 타입을 parameter로 지정할 수 있다. Model객체는 controller에서 생성된 data를 데이터를 view단에 넘겨줄 때 사용하는 객체이다. controller의 method에 Model 객체가 parameter로 지정되었다면 스프링에서 Model 객체를 주입해준다. <br>

Model 객체는 HashMap의 형태를 가지고 있다. key값과 value값을 사용하며 view단에 템플릿엔진에서 Model을 통해 넘어오는 값을 key를 통해 이용할 수 있다. <br>

코드로 확인해보자. <br>

test하기 위해 임의로 만든 controller이다
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

<br>

이렇게 되면 data라고 되어있는 곳에 value값인 "컨트롤러에서 넘어온 값입니다."가 들어오게 되는거다. <br>

다시한번 정리하자면 <br>

controller class안에 method에는 특별하게 Model 객체를 parameter로 받을 수 있는데 Model을 생성해서 직접 넣어주는 것이 아니라 브라우저를 통해 요청을 받게되면 spring MVC에서 Model 타입의 객체를 만들어 주기 때문에 개발자 입장에서는 필요한 데이터만 담아주면 된다. **Model을 사용하는 경우는 주로 controller에 전달된 데이터를 이용해서 추가적인 데이터를 만들어내어 view에 보내줄 때 사용하게 된다.

<br>

---

## 참조

코드로 배우는 스프링 웹 프로젝트 - 구멍가게 코딩단.
스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)