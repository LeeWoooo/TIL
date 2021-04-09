Vue의 Component
===

Component란 조합하여 화면을 구성할 수 있는 블록(화면의 특정 영역)을 의미한다. 컴포넌트를 활용하면 화면을 빠르게 구조화하여 일괄적인 패턴으로 개발을 할 수 있게 된다. 이로써 얻는 장점으로는 화면의 영역을 컴포넌트로 쪼개서 재활용할 수 있는 형태로 관리하면 나중에 유지보수가 좋아진다는 장점을 가지고 있다.

Vue에서는 웹 화면을 구성할 때 흔히 사용되는 영역들을 컴포넌트 단위로 관리를 한다.

* 사진

    <img src = https://kr.vuejs.org/images/components.png>

이와 같이 사용자에게 보여지는 영역을 Component 단위로 관리를 하며 Component는 화면을 구성하는 데 매우 중요한 역할을 하며 Component 간의 관계는 자료구조의 Tree모양과 유사하다.

>Tree란 컴퓨터 자료구조 중 하나로 노드끼리의 연결이 부모-자식의 구조를 갖는다.

<hr>

## Component 등록하기

컴포넌트를 등록하는 방법은 전역과 지역 두가지의 방법이 있다. Local Component는 특정 인스턴스에서 유효한 범위를 가지고 있고 Global Component는 여러 인스턴스에서 공통으로 사용할 수 있다.

<br>

### Global Component로 등록하기

Global Component는 Vue 라이브러리를 로딩하고 나면 접근 가능한 Vue 변수를 이용하여 등록한다. Global Component를 모든 인스턴스에 등록하려면 Vue생성자에 <code>.component()</code>를 호출하여 수행하면 된다. 

* 코드

    ```javascript
    Vue.component('Component이름',{

    })
    ```

Global Component 등록 형식에는 Component이름과 Component내용이 있다. Component 이름은 template 속성에서 사용할 HTML 사용자 정의 태그 이름을 의미한다. ```생성순서는 먼저 Vue 생성자로 Component를 등록하고 그 다음 Vue 인스턴스 객체들이 생성이 된다.```

>사용자 정의 태그 : HTML 표준태그들 이외에도 웹 개발자가 집접 정의하여 사용할 수 있는 태그

Component tag가 실제 화면의 HTML 요소로 변환될 때 표시될 속성들을 Component 내용에 작성을 한다. 여기에 template,data,methods등을 정의할 수 있게 되는 것이다.

<br>

### Local Component 등록

Local Component 등록은 인스턴스에 components 속성을 추가하고 등록할 컴포넌트 이름과 내용을 정의하면 된다.

* 코드

    ```javascript
    new Vue ({
        components : {
            'Component이름' : Component 내용
        }
    });
    ```

Component 이름은 HTML에 등록할 사용자 정의 태그를 의미하며 내용으로는 Option속성들이 들어가게 된다.


<hr>

## Local Component와 Global Component의 차이

Local Component와 Global Component의 차이점은 Vue 인스턴스와 관계가 있다. Global Component는 어떠한 Vue 인스턴스에서도 접근이 가능하지만 Local Component는 Vue인스턴스를 생성하고 사용하는 그 범위 안에서만 사용이 가능하다. 코드를 보면서 확인을 해보자면 먼저 Golbal Component를 하나 생성하고

* 코드

    ```javascript
        Vue.component('my-component',{
            template : '<div>전역 컴포넌트가 등록되었습니다!</div>',
            data : {
                data1 : 'data1'
            }
        });
    ```

그 이후 두 개의 Vue 인스턴스를 생성할 것인데 하나는 Local Component를 가지고 있는 인스턴스이고 하나는 없는 인스턴스를 생성한다.

* 코드

    ```javascript
    new Vue({
            el:'#app',
            components:{
                'my-local-component' : {
                    template : '<p>Hello Vue</p>'
            }
        });

    new Vue({
            el:'#app2'
        });
    ```

이 후 HTML에서 Component를 사용해보면 차이를 알 수 있다.

* 코드

    ```html
        <div id="app">
            <my-component></my-component>
            <my-local-component></my-local-component>
        </div>

        <div id="app2">
            <my-component></my-component>
            <!-- 여기서 인스턴스 유효범위 밖이기 때문에 app2에서는 my-local-component를 찾지 못한다. -->
            <my-local-component></my-local-component> 
        </div>
    ```

my-local-component는 유효범위가 app안에서만 사용이 가능하기 때문에 app2에서는 my-local-component를 찾지 못한다. 또한 Global Component는 <code>.component</code>를 사용하지만 Local Component는 <code>components</code>를 사용하여 선언한다.