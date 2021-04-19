Single-page application vs. multiple-page application
===

웹 어플리케이션이 이전 데스크탑 응용 프로그램을 대체하고 있다. 사용하기 좀 더 편리하고, 업데이트가 쉬우며, 단 한가지 기기에만 종속되지 않기 때문이다.

또한 모바일에 대한 수요가 증가하면서 데스크탑 응용 프로그램에서 웹 어플리케이션에 대한 필요성이 대두되고 있다.

웹 어플리케이션을 만들 때 사용하는 대표적인 디자인 패턴이 있다.

1. Single-page application
2. multiple-page application

<br>

---

<br>

## Single-page application

단일 페이지 어플리케이션은 브라우저 내부에서 동작하는 어플리케이션이고 사용하는 동안 페이지 리로딩이 필요없다. 이런 SPA의 대표적인 서비스는 Gmail,google Map, facebook, github 등이 있다. 

* 사진 

    <img src = https://user-images.githubusercontent.com/74294325/115260980-3275fd80-a16e-11eb-9367-846e5dac836d.png>


Single-page application의 장점

1. SPA는 정적 리소스(HTML,CSS,Js)가 앱의 생명주기 동안 딱 한번만 로드되기 때문에 로드 된 이후에는 빠르게 동작한다.

2. 개발이 간단하고 능률적이라는 장점이 있다. 페이지를 렌더링하기 위해 서버에 코딩을 할 필요가 없으며 서버 이용없이 파일로 개발을 시작할 수 있다.

3. 크롬 개발자도구를 활용해 디버깅 작업을 쉽게 할 수 있다.

4. 개발자는 웹과 모바일 앱의 동일한 BackEnd 코드를 재사용할 수 있기 때문에 모바일 웹을 만들기가 더 쉽다.

5. SPA는 LocalStroge를 효율적으로 사용할 수 있다.

Single-page application의 단점

1. SPA의 장점 1번이 단점이 될 수도 있다. 정적 리소스를 처음에 모두 로드하기 때문에 리소스가 클 경우에 시간이 소요된다.

2. javascript를 사용할 줄 알아야 한다.(SSR를 이용하여 서버에서 페이지를 렌더링 할 수 있지만 JS가 없다면 웹의 다른 기능들이 문제가 생길 수 있다.)

3. javascript의 메모리 누수가 시스템을 느리게 만들 수 도 있다.

<br>

---

<br>

## multiple-page application

MPA는 클라이언트에게 요청이 오게 되면 요청에 대한 응답으로 HTML을 응답하게 된다. 이러한 과정에서 페이지 전체가 리로딩 되게 된다. 비동기 통신(ajax,axios 등...)을 이용하여 요청이 들어온 부분에 대한 응답을 하여 페이지 전체가 아닌 일부만 변경되도록 처리를 할 수 있다.

* 사진 

    <img src = https://user-images.githubusercontent.com/74294325/115261155-55a0ad00-a16e-11eb-9acd-2b7401d0e711.png>

multiple-page application의 장점

1. 어플리케이션이의 흐름을 쉽게 파악하기 쉽다.

2. 쉽고 정확한 검색 최적화를 제공해준다. 이미 완성된 컨텐츠를 담고 있는 페이지를 랜더링 하기 때문이다.

3. 이전부터 사용해왔던 패턴이기 때문에 접근하기 쉽고 예제가 많다.

multiple-page application의 단점

1. FrontEnd와 BackEnd가 밀접한 관계를 가지고 있다. 

2. 개발을 할 때 클라이언트와 서버사이드에서 각각의 프레임워크를 사용해야 한다.

3. 서비스가 되는 페이지라고 예를 들었을 때 수많은 사용자가 동시에 서버에 요청을 하게 될 경우 요청에 대한 응답을 서버에서 감당하지 못하고 다운될 수도 있다.

<br>

---

<br>

## 그럼 언제 SPA를 사용하고 언제 MPA를 사용할까?

어떠한 것을 사용해야 한다라는 정답은 없다. 앞으로 만들어질 웹 어플리케이션이 어떠한 성격을 띄고 있는지, 어떠한 클라이언트를 타겟으로 하고 있는지, SEO가 중요한 서비스 인지 등을 파악하고 SPA와 MPA에 대한 이해를 동반하여 결정을 하면 될 것 같다.