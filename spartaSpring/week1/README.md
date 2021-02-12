1일차
===

## @RestController

스프링 4에 생긴 Annotation이다. @Controller는 해당 class가 컨트롤러 역할을 한다는 것을 알려주고, 그 안에서 @RequestMapping이 붙은 method를 해당 URL 패턴에 맞게 실행시켜 return에 기입된 jsp문서를 반환하는 역할을 했다. 

<br>

즉 정보의 처리와, jsp에 대한 처리를 스프링 프로젝트를 돌리고 있는 서버 컴퓨터에서 전부 하고, 그 완성된 jsp문서를 클라이언트에게 전송가는 개념이다. 

<br>

@RestController는 Rest통신을 지원하는 Controller로서, Controller Annotation이 클라이언트가 보낸 요청에 따라 class안에서 처리한 정보를 jsp라는 혈식으로 클라이언트에게 응답하는 것이라면, **RestController는 요청을 받고 정보를 처리하는 것 까지는 동일한데 class안에 포함되는 @RequestMapping이 붙은 emthod들이 return하는 값이 jsp문서가 아니라 json 형식으로 반환이 된다.**

<br>

@RestController를 사용하면 좋은 점은 기존 jsp로 응답을 하는 경우에는 jsp형식으로만 정보를 받게되는데 json형식으로 데이터를 받게되면 요청한 정보만 보여줄 수 있는 유연함이 생긴다. <br>

스프링에는 DTO(VO)를 json형식으로 변형해주는 라이브러리가 있다(jackson-json) 이로 앤해 RestController의 method return타입으로 VO객체를 리턴해주면 스프링이 알아서 json형식,문자열로 변형해준다.

<br>

코드로 확인해보자.

```java
//json으로 응답을 해주는 자동응답기 선언
@RestController
public class CourseController {
    //mapping명으로 요청이 오면 지금 현재의 course 객체를 Json형태로 응답해준다. (스프링이 json으로도 변환해준다.)
    //method의 실행은 요청이 오면 spring이 실행을 해준다.
    @GetMapping("/courses")
    public Course getCourse(){
        Course course = new Course();
        course.setTitle("웹개발의 봄 스프링");
        course.setTutor("남병관");
        course.setDays(35);
        return course;
    }
}
```

<br>

간단하게 이야기하면 Rest는 서버의 응답이 JSON 형식임을 나타고 Controller는 자동응답기라고 생각을 하면 된다. (클라이언트가 요청하면 서버에서 응답하는 제일 앞에 있는 녀석) <br>

Rest + Controller = JSON형식으로 응답하는 자동 응답기라고 이해해보자.(JSON만을 돌려주는 것은 RestController라고 부른다.) class에 annotation으로 RestController라고 선언해줌으로 json형식으로 반환하는 자동응답기라는 것을 선언해준다. (스프링한데 이놈이 RestController라고 말해준다.)