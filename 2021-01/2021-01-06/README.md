Today I Learned (TIL)
===

## 2021-01-05
---

### 한 일


1. SIST강의(CLOB , CallableStatement)

    * CLOB

        * VARCHAR2보다 더 많은 양의 text를 받을 수 있다(4GByte)

        * ResultSet에서 CLOB의 데이터타입을 가진 column을 조회할 때는 Stream을 연결하여 읽어야한다.<br>
        (local에서는 getString()로 가능하지만 web Server에 올라간 것은 Stream으로 연결해야 한다. clob에 getCharacterStream()를 이용한다.)

    * CallableStatement

        * Procedure을 생성하고 CallableStatement를 이용하여 procedure를 호출하여 사용

        * 바인드 변수를 이용해 값을 넣을 때 in parameter와 out parameter를 구분해서 사용한다.

            * in parameter는 set으로 가능하지만 out parameter는 registerOutparameter를 사용하여 등록

2. Swing로 Table만들기 구현 (완료)

    * 배운 기능을 가지고 swing를 이용하여 재사용하면서 기능을 익히는 것 또한 좋은 것 같다.

3. HTML 책 읽기.

    * 생활코딩으로 HTML의 기본적인 개념을 익혀놓은 것을 SIST에서 HTML을 들으면서 더 보강해야겠다.

### 내일 할일 

1. SIST강의 (HTML)

    * was설치 (tomcat)

2. HTML 복습