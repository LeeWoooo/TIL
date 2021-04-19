Vue Template
===

Vue의 템플릿은 HTML,CSS등으 ㅣ마크업 속성과 Vue 이느턴스에서 정의한 데이터 및 로직들을 연결하여 사용자가 브라우저에서 볼 수 있는 형태의 HTML로 변환해주는 속성이다.

템플릿 속성을 사용하는 방법은 두 가지로, 

### 첫번째는 ES5에서 Vue 인스턴스의 template속성을 이용하는 방법이다.

* code

    ```javascript
        <script>    
            new Vue({
                template: '<p>Hello {{message}}</p>'
            })
        </script>
    ```

템플릿 속성은 사용자가 볼 수는 없지만 라이브러리 내부적으로 template 속성에서 정의한 마크업 + Vue data를 가상 돔 기반의 render() 함수로 변환한다. 변환되는 reder() 함수는 최종적으로 사용자가 볼 수 있게 화면을 그리는 역할이다. template의 속성을 사용하는 것과 사용하지 않은 것의 차이는 인스턴스의 내용이 적용되는 시점만 다르다.

* 사용 안했을 경우

    ```javascript
        <div id="app">
            <h3>{{message}}</h3>
        </div>
        ...
        <script>
            new Vue({
                el:"app",
                data:{
                    message:'Hello Vue'
                }
            });
        </script>
    ```

* 사용 했을 경우

    ```javascript
        <div id="app">
        </div>
        ...
        <script>
            new Vue({
                el:"app",
                data:function () {
                    return {
                        message : 'Hello Vue'
                    }
                },
                template : '<h3>{{message}}</h3>'
            });
        </script>
    ```

```사용하지 않았을 경우에는 화면에 먼저 <h3>{{message}}</h3>가 그려지고 Vue 인스턴스가 생성되면 message값이 치환된다.``` 하지만 Vue의 template속성을 사용했을 경우 ```div안에 아무런 내용이 없다가 Vue인스턴스가 생성되면 인스턴스가 mount되어 화면에 그려진다.```

### 두 번째는 싱글 파일 컴포넌트 체계의 &lt;template>를 이용한 방법이다. (추후 다룰 예정)

<hr>

<br>

## 데이터 바인딩

데이터 바인딩은 HTML 화면 요소를 Vue 인스턴스에 데이터와 연결하는 것을 의미한다. 주요 문법으로는 <code>{{}}</code>와 <code>v-bind</code>속성이 있다.

1. {{}}

    {{}}는 Vue 인스턴스의 데이터를 HTML 태그에 연결하는 가장 기본적인 텍스트 삽입 방식이다.

2. v-bind

    v-bind는 아이디,클래스,스타일 등의 HTML 속성값에 뷰 데이터 값을 연결할 때 사용하는 데이터 연결방식이다. 형식은 <code>v-bind</code>속성으로 지정할 HTML 속성이나 props속성 앞에 접두사로 붙인다.

    * 예제 코드

    ```javascript
    ...
    <div id="app">
        //인스턴스 data의 key값에 해당되는 value들이 들어가게된다.
        <p v-bind:id="idA">아이디 바인딩</p>
        <p v-bind:class="classA">클래스 바인딩</p>
        <p v-bind:style="styleA">스타일 바인딩</p>
    </div>

    <script>
        new Vue({
            el:'#app',
            data:function(){
                return {
                    idA:10,
                    classA:'containser',
                    styleA:'color: blue'
                }
            }
        });
    </script>
    ```

    ```v-bind문법을 :로 간소화 할 수 있다.``` 예를들어 v-bind:id와 :id는 같은 동작을 한다.(약식 문법과 기본문법을 혼용해서 사용하는 것을 지양하자.)


<hr>

<br>

## 자바스크립트 표현식

Vue의 템플릿 에서도 자바스크립트의 표현식을 쓸 수 있다. {{}}안에서 자바스클비트 표현식을 넣으면 된다. 주의할 점은 자바스크립트의 선언문과 분기 구문은 사용할 수 없다. 또한 복잡한 연산은 인스턴스 안에서 처리하고 화면에는 간단한 연산 결과만 표시해야 한다.

* 코드

    ```javascript
        <div id="app">
        //{{ let a = 10 ;}} 선언문은 사용이 불가능하다.
        //{{ if(true){return 100}}} 분기 구문은 사용이 불가능
        {{true ? 100 : 0}} //삼항 연산자는 가능

        {{message.split('').reverse().join('')}} //복잡한 연산은 인스턴스 안에서 처리
        {{reversedMessage}}
        </div>
        <script src="https://cdn.jsdelivr.net/npm/vue"></script>
        <script>
            new Vue({
                el:'#app',
                data: function(){
                    return {
                        message : 'Hello Vue'
                    }
                },
                computed:{ //데이터 속성을 자동으로 계산해주는 속성
                    reversedMessage : function(){ //reversedMessage 표현되기 전에 연산을 수행하는 함수
                        return this.message.split('').reverse().join('');
                    }
                }
            })
        </script>
    ```

여기서 복잡한 연산을 HTML단에서 수행하지 않고 인스턴스 안에서 computed속성을 이용하여 계산하는 이유는 코드의 가독성을 높이기 위함이다.

<hr>

<br>

## 디렉티브

Vue 디렉티브란 HTML태그 안에서 v- 접두사를 가지는 모든 속성을 이야기한다.

* 예제 코드

    ```javascript
        <a v-if="flag">Hello Vue</a>
    ```

위의 코드는 인스턴스 데이터 속성에 flag값에 따라 보이기도 하고 안보이기도 한다. flag값이 참이면 보이고 거짓이라면 화면에 보이지 않는다.

디렉티브는 화면의 요소를 더 쉽게 조작하기 위해 사용하는 기능이다.

* 주요 디렉티브

    디렉티브 이름 | 역할
    :--- | :---
    v-if | 지정한 Vue 데이터 값의 참, 거짓 여부에 따라 해당 HTML 태그를 화면에 표시하거나 표시하지 않는다.
    v-for | 지정한 Vue 데이터 개수만큼 해당 HTML태그를 반복 출력한다.
    v-show | v-if와 유사하게 데이터 값의 참,거짓에 따라 해당 HTML 태그를 화면에 표시하거나 표시하지 않는다. **다만 v-if는 해당 태그를 완전히 삭제하지만 v-show는 css효과만 display:none;으로 주어 실제 태그는 남아있고 보이지만 않게 되는 것이다.**
    v-on | 화면 요소의 이벤트를 감지하여 처리할 때 사용한다. 예를 들어 v-on:click는 해당 태그의 클릭 이벤트를 감지하여 method를 실행한다.
    v-model | form에서 주로 사용되는 속성이다. **form에 입력한 값을 Vue 인스턴스에 데이터와 즉시 동기화를 한다.** 화면에 입력된 값을 저장하여 서버에 보내거나 watch와 같은 속성을 이용하여 추가로 로직을 생성할 수 있다.

* 예제 코드

    ```javascript
        <div id="app">
            <a v-if="flag">Hello Vue!</a>
            <ul>
                <li v-for="system in systems">{{system}}</li>
            </ul>
            <p v-show="flag">Hello Show Vue!</p>
            <h5 v-bind="uid">아이디 바인딩</h5>
            <button v-on:click="popupAlert">경고창 버튼</button>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
        <script>
            new Vue({
                el:'#app',
                data:function(){
                    return {
                        flag : true,
                        systems : ['android','ios','window'],
                        uid:10
                    }
                },
                methods :{
                    popupAlert : function(){
                        alert('경고창 표시');
                    }
                }
            })
        </script>
    ```

<hr>

<br>

## 이벤트 처리

v-on 디렉티브와 methods 속성을 활용하여 이벤트 처리를 할 수 있다. 위의 예제코드와 같이 버튼이 눌렸을 때 이벤트를 발생시키는 코드는 이러하다.

* 예제 코드

    ```javascript
        ...
        <button v-on:click="popupAlert">경고창 버튼</button>
        ...

        <script>
            new Vue({
               ...
                methods :{
                    popupAlert : function(){
                        alert('경고창 표시');
                    }
                }
                ...
            })
        </script>
    ```

이렇게 되면 버튼이 클릭 되었을 때 alert으로 경고창이 표시되게된다. 또한 v-on 디렉티브로 method를 호출할 때 인자 값을 넘겨줄 수도 있다.

* 코드

    ```javascript
        ...
        <button v-on:click="popupAlert(10)">경고창 버튼</button>
        ...

        <script>
            new Vue({
               ...
                methods :{
                    popupAlert : function(num){
                        alert(num+'번 경고창 표시');
                    }
                }
                ...
            })
        </script>
    ```

마지막으로 event의 인자를 받아서 DOM이벤트에도 접근이 가능하다.

    ```javascript
        ...
        <button v-on:click="popupAlert">경고창 버튼</button>
        ...

        <script>
            new Vue({
               ...
                methods :{
                    popupAlert : function(event){
                        alert(event);
                    }
                }
                ...
            })
        </script>
    ```

<hr>

<br>

## 고급 템플릿 기법

### computed 

데이터를 가공하는 등의 복잡한 연산은 Vue 인스턴스 안에서 하고 최종적으로 HTML에는 데이터를 표현만 해야하는데 이 때 computed 속성은 이러한 데이터 연산들을 정의하는 영역이다. 위에 예제코드에서도 한번 사용했지만 다시한번 코드로 보자면

* 예제 코드

    ```javascript
        <div id="app">
        ...
        {{reversedMessage}}
        </div>
        <script src="https://cdn.jsdelivr.net/npm/vue"></script>
        <script>
            new Vue({
                ...
                data: function(){
                    return {
                        message : 'Hello Vue'
                    }
                },
                computed:{ //데이터 속성을 자동으로 계산해주는 속성
                    reversedMessage : function(){ //reversedMessage 표현되기 전에 연산을 수행하는 함수
                        return this.message.split('').reverse().join('');
                    }
                }
                ...
            })
        </script>
    ```

computed 속성의 장점은 ```data 속성 값의 변화에 따라 자동으로 다시 연산한다는 점이다.``` 예를 들어, computed 속성에서 사용하고 있는 data 속성 값이 변경되면 전체 값을 다시 연산한다. 또한 캐싱을 통해 연산을 반복하는 것이 아니라 ```연산 결과를 미리 저장하고 있다가 필요할 때 불러오는 동작을 한다.```

computed 속성과 methods의 차이점은 **methods는 호출할 때만 해당 로직이 수행되고 computed 속성은 대상 데이터의 값이 변경되면 자동적으로 수행된다는 점이다.** 다시 이야기 하자면 수동적으로 데이터를 갱신하느냐? 아니면 능동적으로 데이터를 갱신하느냐 차이점이다. (methods 속성은 수행할 때마다 연산을 하기 때문에 별도의 캐싱을 하지 않지만 computed는 데이터가 변경되지 않는 한 이전의 계산 값을 가지고 있다가 필요할 때 반환해준다.)



