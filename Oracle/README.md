Oracle
===

* 관계형 DataBase 입니다.

---
## ORACLE

* 하나의 ORACLE SERVER에 여러 CLIENT가 접속하여 DATABASE를 조작 하며 작업을 진행한다.

## 스키마

* 데이터베이스 설계 구조 즉, 개체, 속성, 관계에 대한 정의이다.

    * 데이터 개체(Entity), 이들의 속성(Attribute), 이들 간에 존재하는 관계(Relationship),<br>
    데이타의 조작 및 이들 데이타 값들이 갖는 제약 조건에 관한 구조 및 정의를 총칭

* 전체적으로 DataBase의 구조를 뜻한다.

* 스키마는 총 3계층으로 외부스키마, 개념스키마, 내부스키마로 구성되어 있다.

---
## SQL

* Structured Query Language

* SQL이란 DATABASE가 이해할 수 있는 질의 언어(내가 원하는 DATA를 SQL을 이용하여 DATABASE에 요청)

* 관계형 DATABASE들은 SQL을 사용한다.
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

## CREATE(Table 만들기)

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

---

## 내가 생성한 Table 검색하기

* SELECT FROM WHERE 명령어를 사용한다.

    * 검색
        ```
        SELECT table_name FROM all_tables WHERE OWNER = '사용자명'
        ```

    * all_tables이라는 table에서 OWNER의 값이 사용자 명과 같은 
        table_name이라는 column의 값을 검색하며

        <img src = https://user-images.githubusercontent.com/74294325/101790428-c6431780-3b45-11eb-9ca6-b7f0383083ee.JPG>

---

## CREATE (INSERT INTO)

* 만든 Table에 값을 입력한다.

* INSERT INTO의 사용

    * 문법

        ```
        INSERT INTO TABLE명(column1명,column2명,,,)
        VALUES(column1에 넣을값,column1에 넣을값,,,);
        ```

    * 예제

        <img src = https://user-images.githubusercontent.com/74294325/101792366-fa1f3c80-3b47-11eb-84cf-0e617622129c.JPG>

---

## READ(SELECT)

* SELECT FROM WHERE로 DATABASE에게 내가 원하는 DATA를 요청한다.

* SELECT의 사용

    * 문법
        ```
        SELECT column명
        FROM TABLE명
        WHERE 조건
        ORDER BY 기준이 되는 컬럼명;
        ```

    * WHERE과 ORDER BY는 상황에 따라 생략이 가능하다.

    * 조건에는 대부분 비교 연산자가 온다.

    * ORDER BY의 조건 (오름차순 ASC, 내림차순 DESC)
    
    * 예제

        <img src = https://user-images.githubusercontent.com/74294325/101791776-4322c100-3b47-11eb-9c49-8e4ce4ba4ccb.JPG>

---

## OFFSET, FETCH

* Oracle 12c release부터 사용할 수 있는 구문.

* OFFSET

    * 검색을 할 때 원하는 ROW부터 검색을 시작할 수 있는 명령어

    * ORACLE의 INDEX는 0부터 시작한다.

    * 페이지 개념에서는 몇번째 페이지 인가를 나타낼 수 있다.

    * 문법
        ```
        SELECT column명
        FROM TABLE명
        WHERE 조건
        OFFSET (검색을 시작할 ROW의 index) ROWS;
        ```

    * 예제

        <img src = https://user-images.githubusercontent.com/74294325/101922861-fc4dcd80-3c11-11eb-9792-874e6d463a37.JPG>

        * 0번째 ROW부터 검색을 시작해서 읽어낸다.

* FETCH

    * 대부분 OFFSET와 같이 사용된다.

    * OFFSET을 사용한 후 가져올 ROW의 갯수를 지정한다.

    * 페이지 개념에서는 1개의 페이지에 ROW가 몇개를 갖는지 나타낼 수 있다.

    * 문법
        ```
        SELECT column명
        FROM TABLE명
        WHERE 조건
        OFFSET (검색을 시작할 ROW의 index) ROWS
        FETCH NEXT (가져올 ROW의 갯수) ROWS ONLY;

    * 예제

        <img src = https://user-images.githubusercontent.com/74294325/101923243-8302aa80-3c12-11eb-9022-284b4f3174ad.JPG>

        * ONLY 명령어는 내가 지정한 ROW의 갯수만 가져온다. 

---

## UPDATE

* UPDATE

    * 테이블에 입력된 값을 수정하는 명령어.

    * WHERE절이 없는 UPDATE문은 의심해봐야 한다.

        * 조건이 없을 시 TABLE에 있는 모든 값이 바뀐다.
    
    * UPDATE 후 DATA가 바뀐 것을 확인 후 COMMIT;

    * 문법
        ```
        UPDATE TABLE명
        SET
        column1명 = 변경할 값,
        column2명 = 변경할 값,
        ,,,
        WHERE 조건;
        ```

    * 예제

        <img src = https://user-images.githubusercontent.com/74294325/101924145-a8dc7f00-3c13-11eb-903c-1be4d2a11ff9.JPG>

---

## DELETE

* DELETE

    * 테이블에서 ROW를 지우는 명령어

    * WHERE절이 없는 DELETE문은 의심해봐야 한다.

        * 조건이 없을 시 TABLE의 모든 ROW값을 삭제

    * DELETE 후 DATA가 바뀐 것을 COMMIT;

    * 문법
        ```
        DELETE FROM TABLE명
        WHERE 조건;

    * 예제

        <img src = https://user-images.githubusercontent.com/74294325/101924672-4c2d9400-3c14-11eb-9eb4-d101b1a04a03.JPG>

---

## PRIMARY KEY

* PRIMARY KEY

    * 테이블의 기본키.

    * 테이블 당 하나만 정의가 가능하다.<BR>
    (하나의 column으로도 가능하고 두개 이상의 column으로 이루어진 복합키를 기본키로 설정 가능)

    * 기본키는 NULL값이 아니여야하며 유일해야 한다.(개체 무결성)

    * 자동 INDEX가 생성되어 DATA를 검색할 때 속도를 향상시킨다.

    * TABLE을 만들 때 주로 설정해주고 테이블을 만들고 ALTER 명령어를 사용해 지정해줄 수도 있다.

    * 문법
        ```
        //1
        CREATE TABLE 테이블 이름(
	    컬럼명1 DATATYPE [DEFAULT 형식] PRIMARY KEY,
        컬럼명2 DATATYPE [DEFAULT 형식],
        컬럼명3 DATATYPE [DEFAULT 형식],
        컬럼명4 DATATYPE [DEFAULT 형식]
        );

        //2
        CREATE TABLE 테이블 이름(
	    컬럼명1 DATATYPE [DEFAULT 형식],
        컬럼명2 DATATYPE [DEFAULT 형식],
        컬럼명3 DATATYPE [DEFAULT 형식],
        컬럼명4 DATATYPE [DEFAULT 형식],
        CONSTRAINT (제약조건 명) PRIMARY KET (column명)
        );
        ```

        //3
        CREATE TABLE 테이블 이름(
	    컬럼명1 DATATYPE CONSTRAINT [DEFAULT 형식] PRIMARY KEY,
        컬럼명2 DATATYPE [DEFAULT 형식],
        컬럼명3 DATATYPE [DEFAULT 형식],
        컬럼명4 DATATYPE [DEFAULT 형식]
        );

        * column을 선한하면서 지정을 해주거나 제약조건으로 지정해줄 수 있다.

        * 보통은 2번 방식인 제약조건을 이용하여 설정을 해준다.

        * 예제

            <img src = https://user-images.githubusercontent.com/74294325/101927503-eb07bf80-3c17-11eb-9de1-6ce580ea85be.JPG>

---

## SEQUENCE

* SEQUENCE

    * SEQUENCE는 시퀀스란 자동으로 순차적으로 증가하는 순번을 반환하는 데이터베이스 객체이다.

    * 보통 PK값에 중복값을 방지하기위해 사용한다.(기본키와 같이 사용될 때 효율이 배가 된다.)

    * 문법
        ```
        CREATE SEQUENCE SEQUENCE명
        INCREMENT BY 증감숫자 // 증감숫자가 양수면 증가 음수면 감소
        START WITH 시작숫자 // 시작숫자
        NOMINVALUE OR MINVALUE 최솟값
        NOMAXVALUE OR MAXVALUE 최대값
        CYCLE OR NOCYCLE
        CACHE OR NOCACHE;
        ```

        * NOMINVALUE OR MINVALUE 

            * NOMINVALUE : 디폴트값 설정, 증가일때 1, 감소일때 -1028 
            * MINVALUE : 최소값 설정, 시작숫자와 작거나 같아야하고 MAXVALUE보다 작아야함
        
        * NOMAXVALUE OR MAXVALUE

            * NOMAXVALUE : 디폴트값 설정, 증가일때 1027, 감소일때 -1
            * MAXVALUE : 최대값 설정, 시작숫자와 같거나 커야하고 MINVALUE보다 커야함

        * CYCLE OR NOCYCLE
            
            * CYCLE 설정시 최대값에 도달하면 최소값부터 다시 시작 NOCYCLE 설정시 최대값 생성 시 시퀀스 생성중지
        
        * CACHE OR NOCACHE

            * CACHE 설정시 메모리에 시퀀스 값을 미리 할당하고 NOCACHE 설정시 시퀀스값을 메로리에 할당하지 않음

        * 예제
            ```
            CREATE SEQUENCE EX_SEQ //시퀀스이름 EX_SEQ
            INCREMENT BY 1 // 증감숫자 1
            START WITH 1 // 시작숫자 1
            MINVALUE 1 // 최소값 1
            MAXVALUE 1000 // 최대값 1000
            NOCYCLE // 순한하지않음
            CACHE; // 메모리에 시퀀스값 미리할당
            ```

---

