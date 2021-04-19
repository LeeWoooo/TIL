Vue Component 통신
===

Vue는 컴포넌트로 화면을 구성하기 때문에 같은 웹 페이지라도 데이터를 공유할 수 없다. 그 이유는 컴포넌트마다 자체적으로 고유한 유효범위(scope)를 가지고 있기 때문이다.

이는 Vue 프레임워크 내부적으로 정의된 특징이다. 따라서 컴포넌트의 유효범위가 독립적이기 때문에 다른 컴포넌트의 값을 직접적으로 참조할 수 없게 된다. 다음 코드를 보면서 확인해보자.

* 코드

    ```javascript
    const cmp1 = {
            //data의 function에서 return값은 template에서 바로 가져다가 사용할 수 있다.
            template : '<div>첫번 째 지역 컴포넌트 : {{cmp1Data}}</div>',
            data : function(){
                return {
                    cmp1Data : 100
                }
            }
        };

    const cmp2 = {
        //여기서 결과로는 cmp2Data는 아무 값도 출력이 되지 않는다.
        //그 이유는 컴포넌트 끼리는 직접 참조를 할 수 없기 때문이다.
        template : '<div>두번 째 지역 컴포넌트 : {{cmp2Data}}<div>',
        data : function(){
            return {
                cmp2Data : cmp1.data.cmp1Data
            }
        }
    }
    ```

이 처럼 서로 다른 유효범위를 가지고 있는 컴포넌트를 직접적으로 참조할 수 없게 된다.

<hr>

## 상, 하위 컴포넌트 관계

컴포넌트는 각각 고유한 유효 범위를 갖고 있기 때문에 직접 다른 컴포넌트의 값을 참조할 수 없다. 따라서 Vue에서는 자체 정의한 ```컴포넌트 데이터 전달 방법을 따라야 한다.``` 가장 기본적인 데이터 전달 방법은 부모-자식 컴포넌트간의 데이터 전달이다.

* 사진 

    <img src = https://user-images.githubusercontent.com/74294325/114117446-aec44180-9921-11eb-8143-dc6c9174d2d7.png>


<hr>

<br>

## props : 상위에서 하위 컴포넌트로 데이터 전달하기 

props는 상위 컴포넌트에서 하위 컴포넌트로 데이터를 전달할 때 사용하는 속성이다. Vue 인스턴스가 생성이 되면 인스턴스가 최상위 root Component가 되고 그 밑으로 자식 Component들이 추가가 된다. 상위 컴포넌트에서 전달받은 데이터를 하위 컴포넌트에서 사용하기 위해서는 props의 속성을 추가해줘야 한다. 아래의 코드에서 확인해보자면

* 코드

    ```javascript
        ...
        <div id="app">
            //상위 컴포넌트의 data(message)를 전달 받아 하위 컴포넌트의 props에 저장을 한다.(propsdata)
            //4
            <child-component v-bind:propsdata="message"></child-component>
        </div>
        ...
        <script>
            //2
            Vue.component('child-component',{
                //3
                props:['propsdata'],
                //5 (prpos에 있는 것을 template영역에서 바로 가져다 쓸 수 있다.)
                template : '<p>{{propsdata}}</p>'
            });
            //1
            new Vue({
                el:'app',
                data:{
                    message:'Hello Vue! passed from parent Component'
                }
            });
        </script>
        ...
    ```

* 1 : Vue 인스턴스가 생성된다.
* 2 : Vue.component로 컴포넌트가 등록된다.
* 3 : child-component의 내용에 props 속성으로 propsdata를 정의한다.
* 4 : HTML 컴포넌트 태그를 추가하고 v-bind 속성으로 propsdata가 상위 컴포넌트의 message와 바인딩 되어 전달된다.
* 5 : 전달받은 data로 사용자 정의 태그를 그린다.

위의 예제 코드에서 child-component를 전역으로 등록한 것 이외에 딱히 상위 컴포넌트를 지정하지 않았다. 그럼에도 Vue인스턴스 안에 마치 상위 컴포넌트가 존재하는 것처럼 하위 컴포넌트로 props를 내려보냈다. 그 이유는 **컴포넌트를 등록함과 동시에 Vue인스턴스 자체가 상위 컴포넌트가 되기 때문이다.**

<hr>

<br>

## $emit('이벤트명') : 하위에서 상위 컴포넌트로 이벤트 전달하기

하위 컴포넌트에서 상위 컴포넌트로 통신 할 때는 이벤트를 발생시켜(event emit) 상위 컴포넌트에 신호를 보낸다. 상위 컴포넌트에서 하위 컴포넌트의 특정 이벤트가 발생하기를 기다리고 있다가 하위 컴포넌트에서 특정 이벤트가 발생하면 상위 컴포넌트에서 해당 이벤트를 수신하여 상위 컴포넌트의 메서드를 호출하는 것이다.

그럼 여기서 궁금한게 하위에서 상위 컴포넌트로 데이터를 전달할 수는 없을까? 라는 질문이 생기는 데 해결방법은 아래와 같다.(Vue의 단방향 데이터 흐름에 어긋나는 구현방법이기는 하다.)
>이벤트와 함께 데이터를 전달하고 싶은 경우에는 이벤트의 두 번째 인자 값으로 전달하거나 이벤트 버스를 이용한다.

이벤트 발생과 수신 형식은 <code>$emit()</code>,<code>v-on</code>속성을 사용해서 구현한다.

* 코드

    ```javascript
        //이벤트 발생
        this.$emit('이벤트명');

        //이벤트 수신
        <child-component v-on:이벤트명="상위 컴포넌트의 메서드명"></child-component>
    ```

* $emit(): 이벤트 발생
* v-on: 이벤트 수신

코드를 보면서 확인해보자.

* 코드 

    ```javascript
        ...
        <div id="app">
            <child-component v-on:show-log="printText"></child-component>
        </div>
        ...
        <script>
            Vue.component(
                'child-component',{
                    template:'<buttom v-on:click="showLog">show</button>',
                    methods:{
                        showLog:function(){
                            this.$emit('show-log');
                        }
                    }
                });

            new Vue({
                el:'#app',
                data:{
                    message:'Hello Vue! passed from Parent Component'
                },
                methods: {
                    printText: function(){
                        console.log("received an event")
                    }
                }
            })
        </script>
    ```

위의 코드를 설명하자면 
1. show 버튼이 눌리면 
2. showLog의 함수가 실행되고 
3. $emit로 인해 상위 컴포넌트에게 이벤트 명인 "show-log"가 전달된다. 
4. v-on으로 이벤트 명을 수신받아 상위 컴포넌트의 printText가 실행된다.

<hr>

<br>

## 같은 레벨의 컴포넌트 간 통신

상위-하위 관계가 아니라 같은 레벨에 있는 컴포넌트끼리 통신을 하기 위해서는 같은 레벨 위에 있는 상위 컴포넌트로 이벤트를 전달한 후 공통 상위 컴포넌트에서 2개의 하위 컴포넌트에 props를 내려보내는 방법이 있다.

**하지만 이런 통신 구조를 유지하다 보면 상위 컴포넌트가 필요 없음에도 불구하고 같은 레벨 간에 통신하기 위해 강제로 상위 컴포넌트를 두어야 한다는 단점이 있다.**

이러한 방법을 해결 할 수 있는 방법이 ```이벤트 버스```이다.

<hr>

<br>

## 이벤트 버스 : 관계 없는 컴포넌트 간 통신

이벤트 버스를 구현하려면 **애플리케이션로직을 담는 인스턴스와는 별개로 새로운 인스턴슬를 1개 더 생성한다.** 새 인스턴스를 이용하여 이벤트를 보내고 받는다. **보내는 컴포넌트에서는 <code>$emit()</code>를 이용하고 받는 컴포는트에서는 <code>.$on()</code>를 구현한다.

* 코드

    ```javascript
        //이벤트 버스를 위한 추가 인스턴스 1개를 생성
        let eventBus = new Vue();

        //이벤트를 보내는 컴포넌트
        ...
        methods:{
            메서드명 : function(){
                eventBus.$emit('이벤트명',데이터);
            }
        }

        ...
        //이벤트를 받는 컴포넌트
        methods:{
            created:function(){
                enentBus.$on('이벤트명',function(데이터){

                });
            }
        }

    //예제
    <div id="app">
        <child-component></child-component>
    </div>
    <script>
        let eventBus = new Vue();

        Vue.component(
            'child-component',{
                template:'<div>하위 컴포넌트 영역입니다.<button v-on:click="showLog">show</button></div>',
                methods:{
                    showLog:function(){
                        eventBus.$emit('triggerEventBus',100);
                    }
                }
            }
        )

        new Vue({
            el:'#app',
            created:function(){
                eventBus.$on('triggerEventBus',function(value){
                    console.log('이벤트를 전달받음. 전달 값은 :' , value);
                })
            }
        })
    </script>
    ```

예제 코드를 살펴보면 다음과 같다.

1. 먼저 이벤트 버스의 역할을 할 Vue인스턴스를 생성하여 변수에 할당한다. 변수명으로 인스턴스의 속성과 method에 접근이 가능
2. 하위 컴포넌트에 template이라는 속성과 methods를 정의한다. 버튼이 눌리면 methods안에있는 showLog가 실행된다.
3. 버튼이 눌리면 이벤트명과 전달할 데이터를 이벤트 버스에 전달하게 된다.
4. 발생한 이벤트를 받는 곳에서 $on을 이용하여 fuction의 매개변수로 전달 된 값을 받게 된다.

이와 같이 이벤트 버스를 활용하면 props 속성을 이용하지 않아도 원하는 컴포넌트 간에 직접적인 데이터를 전달 할 수 있데 된다. ```하지만 컴포넌트가 많아지면 어디서 어디로 보냈는지 관리가 되지 않는다는 문제가 있기 때문에 이 문제를 해결하기 위한 Vuex(뷰엑스)라는 상태관리 도구를 이용한다(이벤트 버스는 디버깅이 어렵기 때문에 그렇습니다. 소스로만 확인해야 할 수도 있음).```(뷰 엑스는 중,대형 애플리케이션 간의 데이터 관리를 효율적으로 하는 라이브러리 이다.  )



