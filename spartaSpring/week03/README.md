week03
===

## Memo web programing 만들기.

Controller - Service - Repositroy 3개의 계층이 존재하는데 설계 순서는 Repositroy부터 Service, Controller순으로 만들어갈 예정이다. <br>

JPA 및 API의 사용 및 개념과 기술 습득을 하는데 목표를 가지고 있습니다.

<br>

---

<br>

## API 설계하기

메모기능을 하는 web을 만들기 위해서는 4가지의 API가 필요한데 다음과 같이 만들 예정이다. (api -> get:조회 post:생성 put: 수정 delete : 삭제)<br>


기능 | Method | URL | Return
:--- | :--- | :--- | :--- 
메모 생성하기 | POST | /api/memos | Memo
메모 삭제하기 | DELETE | /api/memos | Long
메모 수정하기 | PUT | /api/memos | Long
메모 조회하기 | GET | /api/memos | List&lt;Meno>


<br>

---

<br>

## Entity

Memo에 필요한 column은 작성자, 내용입니다. 코드는 이렇습니다. Lombok을 이용하여 생성자 및 Getter를 생성해주었습니다. 여기서 중요한 것은 @Entity라는 annotation입니다. @Entity가 붙은 클래스는 JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 하며. JPA를 사용해서 테이블과 매핑할 클래스는 반드시 @Entity 를 붙여야 한다. (따로 설정을 하지 않으면 JPA가 table또한 만들어 준다. 기존의 table에 값을 CRUD할 때는 application.properties에서 spring.jpa.hibernate.ddl-auto=none 으로 설정을 해준다. 이후 Mapping할 Table을 @Table을 이용하여 지정해준다.)

```java

//Memo

@Entity
@NoArgsConstructor
@Getter
public class Memo extends TimeStamped{

    @Id
    //id값을 자동으로 증가하도록 
    //(GenerationType의 다른 속성들을 이용하여 시퀀스나 db에게 역할을 넘길수도 있다.)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String contents;

    public Memo(MemoDTO memoDTO){
        this.userName = memoDTO.getUserName();
        this.contents = memoDTO.getContents();
    }

    public void update(MemoDTO memoDTO){
        this.userName = memoDTO.getUserName();
        this.contents = memoDTO.getContents();
    }
}

//TimeStamped

@MappedSuperclass //상속을 받게되면 자동으로 column으로 인식하게 된다.
@EntityListeners(AuditingEntityListener.class)//지켜보고 있다가 변경이 생기면 자동으로 업데이트를 해준다.
@Getter 
public abstract class TimeStamped {

    @CreatedDate//생성시간
    private LocalDateTime createAt;

    @LastModifiedDate//수정시간
    private LocalDateTime modifiedAt;
}
```

<br>

이처럼 TimeStamped class를 추상class로 설정을 한 후 @MappedSuperclass를 사용하여 상속을 받게되면 상속받은 Entity에서 column으로 인식하도록 하였으며 @CreateDate와 @LastModifiedDate를 만들어 생성시간 및 수정시간을 갖도록 하였다. @EntityListeners(AuditingEntityListener.class)는 간단히 이야기하면 스프링에서 주시하고 있다가 수정이 일어나면 알아서 update를 해준다. 이 annotation을 사용할 때에는 main class에서 @EnableJpaAuditing를 이용해 Auditiong을 활성화 시켜준다!

<br>

---

<br>

## Repositroy

지금 강의를 들으며 공부하는 것은 JPA의 사용 및 기본적인 개념이기 때문에 쌩 JPA가 아닌 스프링 부트가 JPA를 한번 감싼 Spring Data JPA를 사용하여 작성할 것입니다. <Br>

Spring Data JPA는 Spring에서 제공하는 모듈 중 하나로, 개발자가 JPA를 더 쉽고 편하게 사용할 수 있도록 도와준다. 이는 JPA를 한 단계 추상화시킨 Repository라는 인터페이스를 제공함으로써 이루어집니다. 사용자가 Repository 인터페이스에 정해진 규칙대로 메소드를 입력하면, Spring이 알아서 해당 메소드 이름에 적합한 쿼리를 날리는 구현체를 만들어서 Bean으로 등록해준다. <br>

Spring Data JPA가 JPA를 추상화했다는 말은, Spring Data JPA의 Repository의 구현에서 JPA를 사용하고 있다는 것입니다. 

```java
public interface MemoRepository extends JpaRepository<Memo,Long> {
        List<Memo> findAllByOrderByModifiedAtDesc();
}
```

<br>

또한 Spring Data JPA는 Query Creation를 제공하는데 규칙에 맞게 조합해서 작성을 하면 조건을 부여하여 검색을 할 수 있게된다. 위의 코드에서 'List<Memo> findAllByOrderByModifiedAtDesc();' 또한 정해진 규칙에 맞게 작성한 일종의 query문이다. doc을 확인하면 규칙을 알 수 있다.(https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)

<br>

---

<Br>

## Service

Service에서 비지니스 로직을 작성해준다. 현재 Memo에 관련된 로직은 Memo작성, Memo 조회, Memo수정, Memo삭제 (CRUD가 필요하다.) 코드는 다음과 같다.

```java

private final MemoRepository memoRepository;
    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }
    @Transactional
    public class MemoService {

        private final MemoRepository memoRepository;

        @Autowired
        public MemoService(MemoRepository memoRepository) {
            this.memoRepository = memoRepository;
        }

        public Long memoUpdate(Long id, MemoDTO memoDTO){
            Memo foundMemo = memoRepository.findById(id).orElseThrow(
                    ()-> new NullPointerException("해당 아이디는 존재하지 않습니다.")
            );
            foundMemo.update(memoDTO);
            return id;
        }

        public List<Memo> findAll(){
            return memoRepository.findAllByOrderByModifiedAtDesc();
        }

        public Long deleteMemo(Long id){
            memoRepository.deleteById(id);
            return id;
        }

        public Memo insertMemo(MemoDTO memoDTO){
            return memoRepository.save(new Memo(memoDTO));
        }
    }
```

<br>

JPA를 사용할 때 @Transactional를 지정해줘야 한다. 그 이유는 JPA는 Transactional안에서 실행을 해야되기 때문이다. 또한 Service class는 MemoRepository를 의존하고 있다. 그렇기 때문에 Bean으로 등록된 MemoRepository를 생성자 주입을 통해 DI를 받게된다!

<br>

---

<br>

## Controller

이제 클라이언트와 가장 가까운 Controller를 작성하려 한다. REST API를 작성하여 각각 method에 맞게 응답을 하도록 할 예정이다 위에서 API를 설계했던것을 기본으로 작성을 하면 된다. Controller는 Service class를 의존하고 있기 때문에 이 또한 DI로 받아야 한다. 코드를 통해 확인해보자.

```java
@Controller
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/api/memos")
    @ResponseBody
    public List<Memo> findAll(){
        return memoService.findAll();
    }

    @PostMapping("/api/memos")
    @ResponseBody
    public Memo insertMemo(@RequestBody MemoDTO memoDTO){
        return memoService.insertMemo(memoDTO);
    }

    @PutMapping("/api/memos/{id}")
    @ResponseBody
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoDTO memoDTO){
        memoService.memoUpdate(id,memoDTO);
        return id;
    }

    @DeleteMapping("/api/memos/{id}")
    @ResponseBody
    public Long deleteMemo(@PathVariable Long id){
        return memoService.deleteMemo(id);
    }
}
```

<Br>

여기서 클라이언트쪽에서 form을 통해 입력을 하는것이 아닌 ajax통신을 통해 JSON으로 데이터를 넘겨받고 응답을 html로 하는것이 아니기 때문에 @RestController를 사용해도 되지만 지금은 기본적인 기능들을 익히기 위해 @Controller와 @ResponseBody를 이용하였습니다. 기본적인 CRUD기능이며 POST방식으로 요청이 할 때 spring은 Content-Type을 application/json으로 강제하고 있다. 그렇기 때문에 ARC로 TEST를 할 때에도, ajax으로 넘길 때에도 Content-Type을 잘 지정해줘야한다.(api를 통해 통신을 할 때 주로 json을 사용하며 응답 또한 json을 이용하게 된다) <br>

@RequestBody는 클라이언트 쪽에서 json data를 같이 요청을 해줄텐데 받은 data를 객체에 넣어주기 위해 사용하는 것으로 넘어오는 json의 key값과 객체의 변수명이 일치해야 넘어오게 된다. <br>

**ajax으로 통신할 때도 ajax통신 옵션에 contenttype : application/json으로 설정은 필수 이다**

<br>

### ARC 결과

* POST

    <img src = https://user-images.githubusercontent.com/74294325/108365494-a1041100-723a-11eb-9e31-117b668d5ea3.JPG>

    ```javascript
    $.ajax({
        type: "POST",
        url: "api/memos",
        data:JSON.stringify(data),
        contentType:"application/json"
    }).done(function (response){
        alert('메세지가 성공적으로 작성되었습니다.');
        $("#contents").val(""); //textarea 초기화
        getMessages();
    })
    ```

<br>

* put

    <img src = https://user-images.githubusercontent.com/74294325/108365532-acefd300-723a-11eb-98ff-f1d07c59788d.JPG>

    ```javascript
    $.ajax({
        type:"PUT",
        data:JSON.stringify(data),
        url:`api/memos/${id}`,
        contentType: "application/json"
    }).done(function (response){
        alert('수정이 완료되었습니다.');
        getMessages();
    })
    ```

<br>

* get

    <img src = https://user-images.githubusercontent.com/74294325/108365581-b711d180-723a-11eb-8d70-e9bf06fc3a53.JPG>

    ```javascript
    $.ajax({
        url:"api/memos",
        type:"GET"
    }).done(function (response){
        $.each(response,function (index,item){
            addHTML(item.id,item.userName,item.contents,item.modifiedAt);
        })
    })
    ```

<br>

* delete

    <img src = https://user-images.githubusercontent.com/74294325/108365619-c09b3980-723a-11eb-80f2-bd9fae8ef852.JPG>

    ```javascript
    $.ajax({
        type:"DELETE",
        url:`api/memos/${id}`
    }).done(function (response){
        alert('삭제가 완료되었습니다.')
        getMessages();
    })
    ```

