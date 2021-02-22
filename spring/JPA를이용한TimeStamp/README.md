JPA Auditing으로 생성시간 수정시간 자동화하기.
===

보통 Entity에는 해당 데이터의 생성시간과 수정시간을 포함하게된다. 언제 만들어졌는지, 언제 수정이됬었는지 등은 차후 유지보수에 있어 굉장히 중요한 정보이기 때문이다. 그렇다 보니 매번 DB에 삽입하기전(Insert), 갱신하기전(update) 날짜 데이터를 등록/수정하는 아래와 같은 코드들이 들어가게 된다.

```java
//생성일 추가 코드 예제
public void savePosts(){
    ...
    posts.setCreateDate(new LocalDate());
    postsRepository.save(posts);
    ...
}
```

<br>

이런 단순하고 반복적인 코드가 모든 테이블과 서비스 method에 포함되어야 한다고 생각을 하면 어마어마헤게 귀찮고 코드가 지저분해질 것이다. 이 문제를 해결하고자 JPA Auditing를 사용합니다. 들어가기전에 앞서 Java1.8부터 추가된  LocalDate와 LocalDateTime 및 JPA Auditing를 간단하게 살펴보자.

<br>

### JPA Auditing

Java에서 ORM 기술인 JPA를 사용하여 도메인을 관계형 데이터 베이스 테이블에 mapping할 때 공통적으로 도메인들이 가지고 있는 필드나 column들이 존재한다. 대표적으로 생성일자, 수정일자,식별자같은 필드 및 column들이 존재한다. <br>

DB에서는 누가, 언제 하였는지 기록을 잘 남겨놓아야 한다. 그렇기 때문에 생성일자,수정일자 column은 굉장히 중요한 데이터이다. <br>

JPA에서는 Audit기능을 제공하고 있는데 Audit는 간단히 말하면 감시한다는 것으로 볼 수 있다. Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능이다. <br>

도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후 update를 하는 경우 매번 시간 데이터를 입력하여 주어야 하는데 Audit를 이용하면 자동으로 시간을 mapping하여 DB 테이블에 넣어주게 된다. <br>

### LocalDate, LocalTime, LocalDateTime

기존 날짜를 사용할 때에는 Calendar, Date를 사용했을 것이다. 하지만 Calendar, Date에는 단점이 존재한다. 그렇기에 Java1.8이후 버전부터는 사용을 권장하지 않는다. 어떠한 단점이 있냐면

1. 불변 객체가 아니다.(즉 누군가가 변경이 가능하다.)
2. 잘못된 month 설정 (ex 10월을 나타내는 값을 9로 표현)

<br>

위에 적은 문제들 말고도 다른 문제들도 존재하고 있다. 이러한 문제들을 해결하기 위해 LocalDate, LocalTime, LocalDateTime를 지원해주는 것이다.

<br>

---

<br>

## 적용하기

Java Class를 하나 만들어 TimeStamp의 기능을 구현하는 코드는 아래와 같다.

```java
@Getter //getter method를 만들어 주는 lombok
@MappedSuperclass //이 class를 상속받게되면 상속받은 entity에서 column의로 적용
@EntityListeners(AuditingEntityListener.class) //현재 class를 audition에 등록
public abstract class Timestamped { //이 class를 단독으로 생성해서 쓸일은 없기 때문에

    @CreatedDate //생성시점
    private LocalDateTime crateAt;

    @LastModifiedDate //수정시점
    private LocalDateTime modifiedAt;

}
```

<BR>

위의 코드에 조금 더 설명을 더하자면 

1. @MappedSuperclass -> JPA Entity class들이 이 class(Timestamped)를 상속할 경우 필드들(crateAt,modifiedAt)도 column으로 인식하도록 한다.

2. @EntityListeners(AuditingEntityListener.class) -> 현재 class에 Auditing기능을 포함시킨다.

3. @CreatedDate -> Entity가 생성되어 저장될 때 시간이 자동 저장된다.

4. @LastModifiedDate -> 조회된 Entity값을 변경할 때 시간이 자동으로 저장된다.

5. @Getter -> 롬복을 이용하여 Get method를 생성한다. 현재 Timestamped에서 Getter를 만들지 않으면 상속받는 Entity class에서 crateAt,modifiedAt에 대한 조회가 되지 않는다.

<br>

이 후 JPA Auditing annotation들을 모두 활성화할 수 있도록 application class에 활성화 annotation을 추가해준다.

```java
@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication //spring boot임을 선언
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Week04Application.class, args);
    }
}
```

<br>

이전 작성해둔 코드를 예제로 사용하여 살펴보자. TimeStamped를 상속받는 Entity class이다.

* Entity

```java
@Entity
@NoArgsConstructor
@Getter
public class Person extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addr;

    public Person(PersonDTO pDTO){
        this.name = pDTO.getName();
        this.addr = pDTO.getAddr();
    }

    public Person(String name, String addr){
        this.name = name;
        this.addr = addr;
    }

    public void update(PersonDTO pDTO){
        this.name = pDTO.getName();
        this.addr = pDTO.getAddr();
    }
}
```

<br>

위의 Person class는 TimeStamped를 상속받고 있기 때문에 TimeStamped에 filed로 선언된 crateAt,modifiedAt을 column으로 사용하게 된다. 