스프링이란
===

1. 스프링은 프레임 워크이다! -> 즉 프레임(틀), 워크(동작) 간단히 이야기하면 틀 안에서 동작을 한다는 것이다. 프레임 워크의 존재 이유는 틀을 벗어나지 않고 프로그래밍을 할 수 있도록 지원해준다. 그렇기 때문에 이 안에서 개발하는 개발자들은 다 같은 틀 안에서 개발을 하게 된다.

<br>

2. 스프링은 오픈 소스이다. -> 소스코드가 공개되어 있다. 즉 스프링이 어떻게 만들어졌는지 확인을 해볼 수 있다는 것이다. 즉 사용화하여 사용할 수 있다는 이야기가 된다.(그런 실력까지 될 수 있는 개발자가 되기를 희망해본다!)

<br>

3. 스프링은 IoC컨테이너를 가진다. -> Inversion of controller '제어의 역전'이라는 의미를 가지고 있으며 주도권이 스프링에게 있다는 것이다. <Br>

    * 내가 직접 new keyword를 사용하여 instance를 생성하게 되면 이 인스턴스에 대한 주소의 주도권은 객체를 생성한 method에서 갖게 된다. <Br>

    * 하지만 **스프링에서는 수 많은 객체들을 스프링이 스캔(컴포넌트스캔)을 해서 생성하여(Bean) 해당되는 객체를 heap 메모리에 생성하여 갖고 있게 된다.** 생성되는 Bean은 singleton으로 생성이 되며 여러곳에서 하나의 Bean을 가져다 사용하게 될 때 모두 동일한 객체를 사용하게 된다.
    
<br>

4. 스프링은 DI를 지원한다 -> 의존성 주입이라고 하는데 IoC컨테이너에서 관리하는 Bean들을 가져와서 사용을 할 수 있게 된다. 이 때에 Bean으로 등록된 객체는 singleton으로 등록이 되게 되며 필요한 class에서 요청을 하면 스프링에서 등록되어 있는 bean을 넣어주게 된다. (가져다 쓰는 객체는 singleton으로 생성되었기 때문에 새로 생성되는 것이 아닌 다 같은 객체를 가져다가 사용하게 된다.) <br>

    DI는 유연한 프로그래밍을 할 수 있게 된다 결합도를 낮추고 응집도를 높일 수 있는 프로그래밍을 할 수 있게 된다.

<br>

5. 스프링은 많은 filter를 가지고 있다 -> 톰켓이 들고 있는 filter의 기능을 하는 file을 web.xml이라고 하며 스프링 컨테이너가 들고있는 filter는 인터셉터(AOP)라고 한다. 즉 filter의 역할은 입출력에 있어서 검증을 하는 역할을 주로 하게 된다.

<br>

6. 스프링은 많은 annotation을 가지고 있다. -> 

    * compile checking : 컴파일을 할 때 컴파일러에게 알려주는 역할 (예를 들어 대표적인 @Override가 있으며 선언되어 있으면 Override가 잘 되어있는지 확인해준다.)

    * 스프링에서는 annotation으로 객체를 생성한다. 예를들어 @Component가 붙어있는 객체는 스프링이 로딩될 때 Component 스캔을 통해 Bean으로 등록을 하게된다, @Autowired가 붙어있으면 스프링이 DI로 주입을 해주게 된다.(주입받는 class를 리플렉션할 때 @Autowired이 있다면 주입을 해주게 되는것)

    * 리플렉션은 어떠한 class가 멤버로 어떠한 것들을 들고있는지를 분석하는 기법을 의미하는데 runtime시 분석을 하게된다. <Br>


        ```java
        @Component //스프링이 로딩될 때 Bean으로 등록
        public class Test{
            ,,,
        }

        public class Test2{
            private final Test test;

            @Autowired //DI로 주입
            public Test2(Test test){
                this.test = test;
            }
        }
        ```

<br>


7.  스프링은 MessageConverter를 가지고 있고 기본값은 json이다. -> 스프링 controller에서 객체를 반환하게 되면 json으로 반환을 해주게 된다. 이때 @ResponseBody를 함께 사용을 하게 되며 @RestController를 이용해서도 보낼 수 있다. 더 자세한 건 미리 공부하고 정리한 것을 참조하자.([스프링웹개발방법](https://github.com/LeeWoooo/TIL/tree/main/spring/%EC%8A%A4%ED%94%84%EB%A7%81%EC%9B%B9%EA%B0%9C%EB%B0%9C%EB%B0%A9%EB%B2%95)) 

<br>

8. 스프링은 BufferdReader와 BufferdWriter를 쉽게 사용할 수 있다. -> @ResponseBody = BufferedWriter @RequestBody = BufferedReader