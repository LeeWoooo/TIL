welcome page만들기
===

spring.io에 접속을 하면 현재 사용하는 스프링의 version에 해당하는 Reference Documentation를 볼 수 있다. 이전 프로젝트를 생성하고 설정했던 버전이 2.3.8이기에 2.3.8의 Reference Documentation을 참조해 welcome페이지를 작성해보자. <br>

[Reference Documentation](https://docs.spring.io/spring-boot/docs/2.3.8.RELEASE/reference/html/)를 들어가서 확인해보면 welcomepage의 작성법을 다음과 같이 명시하고 있다.

<br>

* 작성 방법

    <img src = https://user-images.githubusercontent.com/74294325/108074494-9c115700-70ac-11eb-8d54-1a08c6a78786.JPG>

<br>

번역기를 돌려서 확인해본 결과는 이러하다 <br>

Spring Boot는 정적 및 템플릿 환영 페이지를 모두 지원합니다. 먼저 구성된 정적 콘텐츠 위치에서 index.html 파일을 찾습니다. 찾을 수없는 경우 인덱스 템플릿을 찾습니다. 둘 중 하나가 있으면 자동으로 응용 프로그램의 시작 페이지로 사용됩니다. <br>

번역을 해서 이렇게 나오지만 정리하자면

1. resources 안에 static 디렉토리에서 index.html을 찾는다.
2. 만약 static에 존재하지 않는다면 resources 안에 templates안에서 찾게된다.
3. 둘 중 하나에 index.html이 존재한다면 그 페이지를 welcompage로 사용한다.

<br>

실습해보기

<br>

우선 welcome page를 설정하지 않은 상태에서 스프링을 실행해서 localhost:8080에 접속하게 되면 이러한 error페이지가 등장하게 된다.<br>

* 페이지

    <img src = https://user-images.githubusercontent.com/74294325/108074946-1f32ad00-70ad-11eb-992b-336f36c200c8.JPG>

<br>

static 안에 index.html을 생성하고 다시 서버를 가동시켜 localhost:8080에 접속하면 이러한 결과가 나온다. <br>

* 페이지

    <img src = https://user-images.githubusercontent.com/74294325/108075392-a5e78a00-70ad-11eb-8543-f542d2619072.JPG>

<br>

static이 아닌 templates안에 생성을 해도 동일한 결과가 나온다.

* 페이지

    <img src = https://user-images.githubusercontent.com/74294325/108075453-bac41d80-70ad-11eb-8b7f-346a9bde58b2.JPG>

<Br>

---

## 참조

Reference Documentation(https://docs.spring.io/spring-boot/docs/2.3.8.RELEASE/reference/html/)
스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)