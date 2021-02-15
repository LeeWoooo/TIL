2주차
===

## JPA (Java Persistence API)

자바 ORM 기술에 대한 표중 명세로, JAVA에서 제공하는 API이다. 스프링에서 제공하는 것이 아니다! <br>

자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스이다. 여기서 중요하게 여겨할 점은 **JPA는 말 그대로 인터페이스이다. JPA는 특정 기능을 하는 라이브러리가 아니다.** <br>

ORM이기 때문에 자바 class와 DB테이블을 mapping을 한다. (SQL mapping가 아니다.)<br>

### ORM

ORM은 DB테이블을 자바 객체로 mapping함으로써 객체간의 관계를 바탕으로 SQL을 자동으로 생성해준다. 또한 ORM은 RDB의 관계를 Object에 반영하는 것을 목적으로 하고있다.

* DB(데이터) <-- mapping --> Object 필드

    * 객체를 통하여 간접적으로 DB를 다룬다.

* 객체와 DB의 데이터를 자동으로 mapping해준다.

    * SQL query가 아니라 **method로 데이터를 조작할 수 있다.**
    * 객체간 관계를 바탕으로 **SQL을 자동으로 생성한다.**

<br>

### JPA 동작과정

* 구조
    <img src = https://user-images.githubusercontent.com/74294325/107949834-330cdf00-6fd9-11eb-8fa8-8c2aa660f9fa.png>

<br>

* JPA는 애플리케이션과 JDBC 사이에서 동작을 한다.

    * 개발자가 JPA를 사용하면 JPA내부에서 JDBC API를 사용하여 SQL을 호출하여 DB와 통신을 한다. 즉 **개발자는 JDBC API를 쓰는것이 아니다.**


<br>

* 저장과정

    <img src = https://user-images.githubusercontent.com/74294325/107950003-75ceb700-6fd9-11eb-997d-e594467292a2.png>

<br>

* MemberDAO에서 객체를 저장하고 싶을 때

    * 개발자는 JPA에 Member객체를 넘긴다.
    * JPA는 Member Entity를 분석한 후 INSERT SQL을 생성하고 JDBC API를 사용하여 SQL을 DB에 날린다.

<br>

* 조회과정

    <img src = https://user-images.githubusercontent.com/74294325/107950142-ad3d6380-6fd9-11eb-998e-99bfae4c3e05.png>

<br>

* Member 객체를 조회하고 싶을 때

    * 개발자는 member의 PK값을 JPA에 넘긴다.
    * JPA는
        1. Entity mapping 정보를 바탕으로 SELECT SQL을 생성한다.
        2. JDBC API를 사용하여 SQL을 DB에 날린다.
        3. DB로부터 결과를 받아온다.
        4. 결과(ResultSet)를 객체에 모두 mapping한다. 

* Query를 JPA가 만들어 주기 때문에 Object와 RDB간의 **패러다임 불일치를 해결**할 수 있다.

<br>

### JPA의 사용이유

1. SQL 중심적인 개발에서 객체중심의 개발

2. 생산성

    * JPA를 사용함으로 유지보수 및 CRUD를 간편하게 할 수 있다.

<br>

---

<br>

