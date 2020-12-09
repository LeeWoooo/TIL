Oracle
===

* 관계형 DataBase 입니다.

---

## 스키마

* 데이터베이스 설계 구조 즉, 개체, 속성, 관계에 대한 정의이다.

    * 데이터 개체(Entity), 이들의 속성(Attribute), 이들 간에 존재하는 관계(Relationship),<br>
    데이타의 조작 및 이들 데이타 값들이 갖는 제약 조건에 관한 구조 및 정의를 총칭

* 전체적으로 DataBase의 구조를 뜻한다.

* 스키마는 총 3계층으로 외부스키마, 개념스키마, 내부스키마로 구성되어 있다.

---
## Oracle 사용자 생성 및 스키마 생성

* Oracle은 여러 사용자가 접속하여 사용하며, 사용자를 만들면 사용자에 속한 스키마가 생성된다.

    * 사용자 생성방법.

        <img src = https://user-images.githubusercontent.com/74294325/101638375-55cac680-3a71-11eb-8ecf-6e8196d30fb1.JPG>

        * CREAT USER "userID" IDENTIFIED BY "비밀번호"; 를 이용하여 사용자를 생성할 수 있다.

        * ORA-65096 Error는 CBD와 PBD의 개념때문이다 -참조[wookoa.tistory](https://wookoa.tistory.com/239)

        * error를 해결하고 다시 명령어를 입력하면 사용자가 생성이 된다.

        * 사용자를 생성 후 종료했다가 다시 실행하여 로그인하면 권한이 없어서 로그인이 되지 않는다.

---

## 권한 부여하기

* 관리자로 로그인을 한 후 GRANT 명령어를 사용하여 권한을 부여한다.

* 현재는 학습 목적으로 DBA(DB관리자) 권한을 부여하지만 실무에서는 DB를 사용하는 사람과 DBA를 관리하는 사람으로 나눠 권한을 부여한다.

    * 권한 부여방법.

        <img src = https://user-images.githubusercontent.com/74294325/101639894-33d24380-3a73-11eb-9c81-0998fbf8949f.JPG>

        * 권한을 부여 후 이전에 만든 아이디로 로그인 하면 접속이 잘 된다.

---

## 표 만들기

* CREATE TABLE 명령어를 이용하여 생성한다.

    * 테이블 생성방법
        ```
        CREATE TABLE 테이블 이름(
	    컬럼명1 DATATYPE [DEFAULT 형식],
        컬럼명2 DATATYPE [DEFAULT 형식],
        컬럼명3 DATATYPE [DEFAULT 형식],
        컬럼명4 DATATYPE [DEFAULT 형식]
        );
        ```

    * 테이블의 이름은 다른 테이블과 중복 될 수 없다.
    * 한 테이블 내에서 컬럼명은 중복 될 수 없다.
    * 컬럼 뒤에 데이터유형은 꼭 지정해야 한다.(추후 데이터를 처리할 때 이용하기 편한 DATATYPE을 사용한다.)
    * NOT NULL은 값이 무조건 있어야 한다는 것을 명시한다.


    * 예제
        ```
        CREATE TABLE topic(
	    id NUMBER NOT NULL,
	    title VARCHAR2(50) NOT NULL,
	    description VARCHAR2(4000),
	    created DATE NOT NULL,
        );
        ```

        <img src = https://user-images.githubusercontent.com/74294325/101643286-51a1a780-3a77-11eb-9cea-63a65c995ca0.JPG>