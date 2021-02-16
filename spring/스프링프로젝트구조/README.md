스프링 (gradle) 프로젝트의 구조
===

* 구조 

    <img src = https://user-images.githubusercontent.com/74294325/108067179-c14d9780-70a3-11eb-85f5-a2498ecc703c.JPG>

<br>


현재 빌드 툴은 gradle을 사용하고 있다. gradle이란 Groovy를 이용한 빌드 자동화 시스템을 이야기 하며 스프링을 공부하고 학습하는 지금은 gradle을 버전을 설정하고 스프링에서 필요한 라이브러리를 가져다주는 역할로만 이해하고 추 후 공부할 예정이다.

<br>

---

<br>


본격적으로 구조를 살펴보자 <br>

## src 

src 안에는 main과 test가 있습니다.

### main

main안에는 java와 resources를 포함하고 있고 각각의 역할은 이러합니다.

1. java

    **자바파일이 모여있는 곳**이며 패키지를 잘 분리해서 자바 class를 생성해서 사용하면 된다. 

2. resources

    **자바 class이외에 사용하는 resources들을 보관하는 곳이다.** DB연결을 위한 자원, 설정파일(xml)등의 파일들이 이 곳에 포함된다.

<br>

### test

테스트를 위한 자바class와 resources를 보관하는 곳입니다. 테스트 코드는 코드를 작성하는일과 동일하게 중요한 역할을 합니다. **요즘 개발에서는 test코드를 굉장히 중요하게 여기며 이 내용은 추후에 공부 할 예정입니다.**

<br>

---

<Br>

## build.gradle

버전을 설정하고 의존하는 라이브러리를 이 파일에 명시해준다 기본적인 구조는 이러하다.

```java
plugins {
    id 'org.springframework.boot' version '2.4.2' //스프링 버전
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'java'
}

group = 'com.hello'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8' //현재 JDK 버전

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories { 
    //라이브러리를 가져올 장소 (default가 mavenCentral이다 사용자가 다른 곳으로 지정가능하다.)
    mavenCentral()
}

dependencies {
    // 현재 프로젝트가 의존하고 있는 라이브러리 들이다.
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'mysql:mysql-connector-java'
    annotationProcessor 'org.projectlombok:lombok'
    //기본적으로 test라이브러리도 포함되어있다.
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

test {
    //test
    useJUnitPlatform()
}
```

<br>

---

<br>

## External Libraries

이 안에는 현 프로젝트에 가져온 라이브러리들이 존재한다. build.gradle에 사용할 라이브러리들만 명시를 했지만 **사용할 라이브러리들이 의존하고 있는 라이브러리들을 gradle이 모두 가져온다.**

<br>

---

<br>

## 주요 라이브러리

### 스프링 부트

* spring-boot-starter-web

    * spring-boot-starter-tomcat : 웹서버
    * spring-webmvc : 스프링 웹 mvc

* spring-boot-starter

    * spring-boot
        * spring-core
    * spring-boot-starter-logging
        *logback,slf4j


### 테스트 라이브러리

* spring-boot-starter-test

    * junit : 테스트 프레임워크
    * mockito : 목 라이브러리
    * assertj : 테스트 코드를 좀 더 편하게 작성하도록 도와주는 라이브러리
    * spring-test : 스프링 통합 테스트 지원


<br>

---

<br>

## 참조

스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술(https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)


