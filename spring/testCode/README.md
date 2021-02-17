Test Code
===

개발한 기능을 실행해서 test를 할 때 자바의 main method를 통해 실행하거나 controller를 통해 해당 기능을 실행하여 test를 할 수 있다. 하지만 여기에는 단점이 존재하게 된다. <br>

1. 실행 및 준비하는 과정이서 시간이 많이 소요된다.
2. 동시에 test를 진행할 수 없다. (한번에 하나만 test가능)

<Br>

이러한 단점을 보안하기위해 Java는 JUnit이라는 프레임워크를 사용한다. <br>

Test 코드를 작성하는 방법에는 대표적으로 두가지 방법이 있는다. 하나는 단위 테스트이 다른 하나는 TDD(테스트 주도 개발이다.) <br>

### TDD

테스트 주도 개발을 의미하며 Test코드를 작성후 테스트에 코드를 맞춰서 작성하는 방식을 이야기한다. <br>

### 단위테스트

기능 단위의 테스트 코드를 작성하는 것을 이야기하며 장점으로는 tomcat에 계속해서 올릴 필요가 없으며 자동검증이 가능하다. 또한 개발자가 만든 기능을 보호할 수 있다는 장점이 있다. <br>

<Br>

---

<br>

## Test 코드를 작성할 때

Test코드는 순서와 의존관계가 없이 진행 되어야 한다. 만약 공용으로 사용하는 변수나 객체가 있을 경우에는 @AfterEach와 같은 annotation을 이용하여 method를 작성한 후  초기화 하여 test를 진행한다. <br>

Test코드는 build될 때 포함되지 않기 때문에 method명을 한글로 설정하여도 관계없다. (조금더 직관적일 수 있다.)

<br>

---

<br>

## Given-When-Then Pattern

Given-When-Then을 이용하면 조금 더 직관적인 테스트 코드를 작성할 수 있다. 이게 정답이라는 것은 아니지만 test코드가 아직 익숙치 않은 나에게는 좋은 양식인 것 같아서 익숙해질 때까지는 이렇게 작성을 하는 편이 좋을 것 같다.

* Given

    테스트를 위해 준비하는 과정으로 테스트에 필요한 변수나 입력값등을 정의한다.

* When 

    실제로 action을 하는 부분이며 test를 하고자 하는 기능을 이 구역에 배정한다.

* then

    테스트가 끝난 데이터를 통하여 예상한 결과가 나오는지 등을 검증하는 구역이다.

<br>

---

<br>

## Junit이 제공하는 anntation

Junit이 제공하는 annotation을 살펴보자면 대표적으로 4가지가 있다.

1. @AfterAll
2. @AfterEach
3. @BeforeAll
4. @BeforeEach

<Br>

각 annotation의 이름만 봐도 알 수 있듯 @AfterAll,@AfterEach가 지정된 method는 Test가 종료된는 시점에서 자동으로 실행이된다. @AfterAll은 2개이상의 Test가 동시에 진행될 때 이 모든 Test가 종료되면 실행이 되는 것이고 @AfterEach는 Test가 각각 종료될 때 마다 실행된다. 반대로 @BeforeAll, @BeforeEach도 존재하는데 동작하는 순서는 @After와 반대이다. 정리를 해보자면.

```
@BeforeAll

@BeforeEach
test1
@AfterEach

@BeforeEach
test2
@AfterEach

,,,

@AfterAll
```

<br>

이와 같은 구조를 갖게된다.

<Br>

---

<Br>

한번 코드를 작성해보면서 확인해보자. 먼저 test코드의 위치는 src 안에 main과 test가 존재하게 되는데 test안에서 작성을 하게된다. 관례적으로 naming을 할 때는 test하고자 하는 class의 이름뒤에 Test를 붙여 naming을 한다. <br>

여기 service class가 있다 이 class를 test하는 코드를 작성해나가면서 test코드의 작성방법을 공부해보려 한다. <br>

또한 조금더 직관적이게 test코드를 작성하고자 junut이 아닌 assertj를 통하여 코드를 작성하였다.(추후 assertj에 대한 공부가 필요합니다.)

<br>

```java
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join (Member member){
        memberValidation(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

    public void memberValidation(Member member){
        Optional<Member> findMember = memberRepository.findByName(member.getName());

        if(findMember.isPresent()){
            Member existMember = findMember.get();
            if(existMember.getName().equals(member.getName())){
                throw new IllegalStateException("해당 이름은 이미 존재합니다.");
            }//end if
        }
    }
}
``` 

<br>

현재 여기에 기능은 4가지가 있다.

1. join
2. findAll
3. findOne

<br>

이 3가지 기능을 test하는 코드를 작성해보자면

### join

```java
@Test
void join() {
    //given
    Member member = new Member();
    member.setName("이우길");

    Member member2 = new Member();
    member2.setName("이우길");

    //when
    memberService.join(member);
    try{
        memberService.join(member2);
    }catch (IllegalStateException e){
        //then
        assertThat(e.getMessage()).isEqualTo("해당 이름은 이미 존재합니다.");
    }
}
```

<br>

Member 객체를 입력받아 join을 하는 것을 검증하는 test코드이다. 위에 있는 service 코드를 확인해보면 같은 이름을 가진 객체는 저장을 할 수 없도록 하였다. 그렇기 때문에 저장이 되는것도 검증을 해야하고 예외가 발생하는 경우도 확인을 해줘야 한다.

<br>

### findAll

```java
void findAll() {
    //given
    Member member = new Member();
    member.setName("이우길");

    Member member2 = new Member();
    member2.setName("이우길2");

    //when
    memberService.join(member);
    memberService.join(member2);
    List<Member> memberList = memberService.findAll();

    //then
    assertThat(memberList.size()).isEqualTo(2);
}
```

<Br>

findAll은 내가 집어넣은 객체의 갯수와 조회를 하였을 때 반환된 list의 size의 값과 동일함을 검증한다.

<br>

### findOne

```java
@Test
void findOne() {
    //given
    Member member = new Member();
    member.setName("이우길");
    
    //when
    memberService.join(member);
    Optional<Member> findMember = memberService.findOne(member.getId());

    //then
    assertThat(findMember.get().getName()).isEqualTo(member.getName());
}
```

<br>

저장이 된 객체의 id값을 통해 객체를 조회한 후 조회된 객체와 내가 저장한 객체의 name값이 같은지를 검증한다.

<Br>

---

<br>

## 참조 

스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)