DI (의존성 주입)
===

의존성(Dependency)이라는 것은 하나의 객체가 다른 객체가 없이는 제대로 된 동작을 할 수 없다는 것을 의미한다. 간단히 이야기 하자면 의존성은 하나의 객체의 상태에 따라 영향을 받는다는 것이다. A 객체가 B객체없이는 동작이 불가능한 상황을 A는 B에 의존적이다라고 표현할 수 있다. <br>

주입(injection)은 말 그대로 외부에서 밀어 넣어주는 것을 의마한다. 그래서 두개의 단어를 합쳐보면 어떠한 객체가 필요한 객체를 외부에서 밀어넣어준다 라는 의미가 된다. <br>

의존성 주입을 사용하려면 의존하고 그 의존에 필요한 객체 뿐만아니라 외부 즉 주입해주는 역할을 수행하는 어떠한 것이 필요한데 이것을 스프링에서는 `ApplicatinContext`라는 존재가 필요한 객체를 생성하고, 필요한 객체를 주입해주는 역할을 한다. 따라서 **스프링을 이용하면 개발자들은 객체와 객체를 분리해서 생성하고 이러한 객체를 엮는 wiring작업의 형태의 개발을 하게 된다. <br>

스프링에서 `ApplicatinContext`가 관리하는 객체를 Bean이라고 부르며 Bean과 Bean사이의 의존관계를 처리하는 방식을 이제부터 알아볼 것이다. <br>

<br>

---

<br>

## Bean 등록

먼저 `ApplicatinContext`가 객체를 주입해주려면 Bean을 스프링컨테이너(IOC)에 등록이 되어있어야 한다. 

1. 컴포넌트 스캔 방법
2. @Configuration를 이용한 Bean등록(자바 파일을 이용)

<br>

### 컴포넌트 스캔

class위에 @component가 존재하면 스프링이 로딩될 때 Bean에 등록이 되게 된다. 꼭 @component가 아니여도 @component를 포함한 annotation이면 등록이 된다. 대표적으로 @component를 포함하고 있는 annotation이 @Controller, @Service, @Repository가 있다. 들어가서 살펴보면 <br>

```java
//Service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {,,,}

//Controller
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {,,,}

//Repository
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {,,,}
```

<br>

이와 같이 @Component를 포함안 annotation들은 spring이 로딩될 때 Bean으로 등록된다. **로딩을 할 때 Component를 스캔하게 되는데** `@SpringBootApplication`를 열어서 살펴보면 다음과 같이 @ComponentScan이 있기 때문에 스캔을 한 후 자동으로 등록을 하게 된다. 

<br>

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {,,,}
```

<br>

### @Configuration를 이용한 Bean등록

java class파일을 하나 생성한 후 class에 @Configuration를 지정해준다. 그 안에서 Bean으로 등록할 class를 하나하나 작성해주면 되는데 작성 양식은 다음과 같다.

<Br>

```java
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
```

<br>

@Bean이라는 annotation을 사용하여 method를 선언하는데 return값으로 Bean에 등록할 객체를 반환해준다. 의존관계를 가지고 있는 객체들은 생성을 할 때 Bean으로 등록되어 있는 객체의 method를 생성자의 매개변수로 넣어주게 되면 DI까지 같이 처리를 할 수있다.

<br>

**이렇게 Bean으로 등록된 객체들은 기본적으로 singleton으로 등록이 된다(유일하게 하나만 등로갷서 공유된다.) 따라서 등록된 Bean을 가져다가 사용하게 되면 모두 같은 인스턴스를 사용하는 것과 같다.** (예외적으로 사용자가 설정하여 sington방식이 아닌 다른 방식을 사용할 수도 있다.)

---

<br>

## DI

스프링에 Bean을 등록했다면 이제 DI를 할 수 있는 조건이 충족되었다. 컴포넌트 스캔방식으로 등록을 했던지, @Configuration를 이용한 Bean등록을 했던지 DI를 사용할 수 있는데 방법에는 3가지가 있다 

1. Filed 주입
2. setter 주입
3. 생성자 주입 (권장하는 방법)

    * final 선언이 가능하다(런타임에서 객체 불변성을 보장)
    * 테스트 코드 작성 용이(필드 주입이나 수정자 주입으로 빈이 주입되어 있으면 Mockito를 이용해 목킹 후 테스트를 진행해야 하지만 생성자 주입의 경우는 객체를 생성해서 넣어주기만 하면 된다.)

<br>

생성자 주입에 대해 알아볼 것인데 생성자 위에 @Autowired를 선언해주면 등록되어 있는 Bean에서 객체를 찾아 `ApplicatinContext`가 주입을 해주게 된다. 코드로 확인하자면 

```java
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

}
```

<br>

이와 같이 MemberController는 MemberService라는 객체에 의존을 하고 있는데 생성자를 통해 주입을 받게된다. 이 때 @Autowired 라는 annotation으로 인해 `ApplicatinContext`가 주입을 해주게 되는것이다.

<br>

또 한가지의 방식으로는 @Configuration를 이용한 Bean등록에서 확인했듯이 Bean을 등록할 때 주입해줄 수 있다.

```java
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}

//service
public class MemberService {

    private final MemberRepository memberRepository;
    
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    ,,,,
}
```

<br>

MemberService는 MemberRepository에 의존적이다. 그렇기 때문에 주입을 받아야 하는데 Bean을 등록할 때 MemberService의 생성자에 memberRepository()를 넣어줌으로써 의존관계를 처리할 수 있게된다. <br>

따라서 MemberService에서는 별도로 @Autowired로 엮어줄 필요가 없게된다.

<br>

---

<Br>

## 그럼 어떠한 상화에서 컴포넌트 스캔을 쓰고 @Configuration를 사용할까.

정형화된 컨트롤러, 서비스, 리포지토리와 같은 코드는 컴포넌트 스캔방식을 주로 사용한다. 하지만 정형화 되어있지 않고 상황에 따라 구현 class를 변경해줘야 할 때에는 @Configuration을 이용하여 빈을 등록하게 된다.

<br>

---

<br>

## 참조

코드로 배우는 스프링 웹 프로젝트 - 구멍가게 코딩단. <br>

스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)