Today I Learned (TIL)
===

## 2021-01-18~19
---

### 한 일

* SIST(javascript)

* JSP/servlet

    * 쿠키와 세션은 클라이어트와 서버의 연결을 유지시켜주는 역할을 한다. 쿠키는 클라이언트 쪽에 연결 정보를 저장하고 세션은 서버쪽에 연결 정보를 저장한다. 그렇기 때문에 쿠키보다는 서버가 보안에 조금 더 안전하다. 

    * JSP 내장객체

* 자바스크립트 todo 리스트 만들기

    * todo리스트의 구조는 form으로 데이터를 입력받고 입력받은 form에 이밴트를 등록하고 submit한다. submit할 때 실행되는 function에는 이밴트를 정지시켜주고 입력된 값을 HTML의 list에 추가해준다. list에 추가하는 일을 하는 function에는 li요소를(li안에는 span,삭제버튼,완료버튼을 추가한다 span안에는 사용자가 입력한 값을 innerText로 넣어준다.) 생성해서 list의 자식에 추가해주는 역할을 한다. id값을 각각 list에 붙여주기 위해 생성되는 자식 li에 id의 값을 준다. 그 후 아이디와 입력되는 값을 하나로 묶는 객체를 생성하여 객체를 저장할 배열을 생성해 넣는다. 이 때 list의 id는 배열의 크기 +1로 설정하여 배열에 객체가 증가할 때마다 자동으로 증가하게 한다. 생성한 배열을 브라우저의 localstorage에 저장한다. 브라우저가 닫히고 열릴때 마다 localstorage에 저장된 배열을 다시 list에 추가해주는 함수를 생성한다.<br> 

### 내일 할일 

* SIST강의 

* 인프런 (jsp,servlet강의 듣기) -> 한글처리, DAO&DTO, Connection Pool


