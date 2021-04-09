Vue 라우터
===

라우터를 이해하기 전 라우팅이 무엇인지 알아야 한다. 라우팅이란 웹 페이지 간의 이동방법을 이야기 한다.
>라우팅 : 어떤 네트워크 안에서 통신 데이터를 보낼 때 최적의 경로를 선택하는 과정이다. 

라우팅은 현대 웹 앱 형태중 하나인 싱글 페이지 애플리케이션(SPA)에서 주로 사용된다.
>싱글 페이지 애플리케이션 : 페이지를 이동할 때마다 서버에 웹 페이지를 요청하여 새로 갱신하는 것이 아니라 미리 해당 페이지들을 받아 놓고 페이지 이동 시에 클라이언트의 라우팅을 이용하여 화면을 갱신하는 패턴을 적용한 애플리케이션이다.

일반적으로 브라우저에서 웹 페이지를 요청하면 서버에서 응답을 받아 웹페이지를 다시 사용자에게 돌려주는 시간동안 화면 상에 깜빡거림 현상이 나타나는데(로딩) 이런 부분들을 라우팅으로 처리하면 깜빡거림 없이 매끄럽게 전환할 수 있다.

<hr>

<br>

## Vue 라우터

Vue 라우터는 Vue에서 라우팅 기술을 구현할 수 있도록 지원하는 공식 라이브러리이다. Vue 라우터를 이용하여 Vue로 만든 페이지 간에 자유롭게 이동할 수 있다.

* 태그와 기능

    태그 | 설명
    :--- | :---
    &lt;router-link to="URL 값"> | 페이지 이동 태그. 화면에서 &lt;a>로 표시되며 클릭하면 to에 지정한 URL로 이동한다.
    &lt;router-view> | 페이지 표시 태그. 변경되는 URL에 따라 해당 컴포넌트를 뿌려주는 영역이다.

예제 코드를 통해 살펴보자면 다음과 같다.

* 코드

    ```javascript
    ...
    <div id="app">
        <h1>라우터 실습</h1>

        <p>
            <!-- Vue라우터에서 지원하는 태그 router-link -->
            <!-- 누르게 되면 Vue라우터를 생성할 때 넣어준 routes의 속성에 따라 component가 결정된다. -->
            <router-link to="/main">Main 컴포넌트로 이동</router-link>
            <router-link to="/login">Login 컴포넌트로 이동</router-link>
        </p>

        <!-- router-link 태그로 페이지 이동시 component가 뿌려질 영역 -->
        <router-view></router-view>
    </div>
    <!-- Vue라우터 CDN방식 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/vue-router@3.0.1/dist/vue-router.js"></script>
    <script>
        //Main 컴포넌트
        let Main={
            template:'<div>main</div>'
        }
        //Login 컴포넌트
        let Login={
            template:'<div>login</div>'
        }

        //routes변수에 URL값에 따른 어떠한 component가 보여줄지 정의한다.
        let routes = [
            {path : '/main', component: Main},
            {path : '/login',component: Login}
        ];

        //router변수로 VueRouter를 생성하고 routes를 삽입하여 URL에 따라 화면이 전환될 수 있게 정의
        let router = new VueRouter({
            //mode:'history'를 이용해 URL에 해시 값(#)을 없앨 수 있다.
            mode:'history',
            routes
        })

        //새 인스턴스를 생성하고 라우터의 정보가 담긴 router를 추가한다.
        let app = new Vue({
            router
        }).$mount('#app');
    </script>
    ...
    ```

여기서 사용된 <code>$mount()</code> API는 인스턴스 옵션중 el과 동일하게 인스턴스를 화면에 붙이는 역할을 한다. 인스턴스를 생성할 때 el속서을 넣지 않더라도 생성하고 나서 $mount()를 이용하면 강제로 인스턴스를 화면에 붙일 수 있게된다.(```참고로 Vue라우터 공식 문서는 모두 인스턴스 안에 el을 지정하지 않고 라우터만 지정하여 생성한다음 생성된 인스턴스를 $mount()를 이용해 붙이는 식으로 안내하고 있다.```)


<hr>

<br>

## Nested Router

네스티드 라우터는 라우터 페이지를 이동할 때 최소 2개 이상의 컴포넌트가 화면에 나타낼 수 있다. 즉 상위 컴포넌트 1개에 하위 컴포넌트 1개를 포함하는 구조를 갖는다.이렇게 되면 상위 컴포넌트 안에서 하위 컴포넌트만 변경하면서 사용이 가능해진다.

여기서 특징은 ROOT에서도 router-view가 사용되고 ROOT밑에 있는 상위 component에도 router-view가 있다. 코드를 확인해보면

* 코드

    ```javascript
        //vue 인스턴스가 적용될 dom
        <div id="app">
            <h1>라우터 실습</h1>
            <router-view></router-view>
        </div>

        //root밑에 있는 상위 component
        const User = {
            //nested 라우팅은 부모 컴포넌트의 template에서 router-view를 갖는다.
            template : `<div>
                            유저 컴포넌트 입니다. 이 안에 자식 컴포넌트가 담길 것입니다.
                            <router-view></router-view>
                        </div>`
        };

        //router을 생성할 때 routes에서 URL에 따른 component를 지정한다.
        const routes = [
            {
                path:'/user',
                component:User,
                //이 /user컴포넌트 밑으로 포함될 컴포넌트 및 URL을 children이라는 배열안에서 각각 객체로 생성한다.
                children:[
                    {
                        path:'profile',
                        component:UserProfile
                    },
                    {
                        path:'post',
                        component:UserPost
                    }
                ]
            }
        ]
    ```

위와 같이 상위 안에서 보여질 하위 컴포넌트를 children이라는 속성의 값으로 객체배열을 이용하여 사용될 component들을 지정해준다.

<hr>

<br>

## Named View

네임드 뷰는 특정 페이지로 이동했을 때 여러개의 컴포넌트를 동시에 표시하는 라우팅 방식이다. 위의 라우팅 방식은 상위 컴포넌트가 하위 컴포넌트를 포함하는 형식이라면 네임드 뷰는 같은 레벨에서 여러개의 컴포넌트를 한번에 표시하게 된다.

* 코드

    ```javascript
        //routes를 설정할 때 한 페이지에서 보여질 component들을 components속성에 선언한다.
        const routes = [
            {
                path:'/',
                components :{
                    header : Header,
                    body : Body,
                    footer : Footer
                }
            }
        ];

        //선언된 component들을 인스턴스 영역에서 다음과 같이 name속성을 이용하여 가져온다.
        <router-view name="header"></router-view>
        <router-view name="body"></router-view>
        <router-view name="footer"></router-view>
    ```