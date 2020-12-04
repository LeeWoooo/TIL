Today I Learned (TIL)
===

## 2020-12-04

---

### 오늘 한 일



1. SIST 강의 듣기 (Network - server와 client를 생성하여 packet전송)

2. 메모장 만들기
    
    * window notepad에서 구현하는 기능중 "새 글","열기","저장","닫기","서식" 구현 완료

    * java에서 제공하는 서식을 이용하였습니다.

    * I/O Stream을 사용하여 파일 저장 및 열기 가능. 

### 오늘 느낀점, 배운점

* 직접 메모장 하나를 만들어 보니 메모장 만들기도 쉬운 일이 아니었다는 것을 경험하였다...

    * 그래도 만들지 않고 이론만 공부하고 배우는 것 보다는 무엇을 만들고 눈으로 보는것이 좋다.

* 부모 class 생성자 에서 예외를 throws하고 있을 경우 이 class를 상속받는 자식 class 생성자에서도 예외를 throws해야 한다.

    * 자식class의 생성자에는 부모class의 생성자인 super();가 가장 윗 줄에 위치한다.

    * 자식class를 이용하여 Instance를 생성할 때 부모class의 생성자가 먼저 호출된다. 그렇기에 부모class가 예외를 던지면 처리해야한다.

    * 하지만 super();을 try~catch로 처리할 수 없다 -> 생성자는 가장 윗줄에 위치해야 하기에 try~catch를 사용하면 error

    * 자식 class의 생성자도 동일한 예외를 던져 작성한다.

        ```java
        public class test{

            public test() throws IOException{

            }
        }
        ```

    * error code
        
        ```java
        public class test2 extends test {

            public test2(){
                try{ // 이 경우 부모class의 생성자가 첫번째 줄에 위치 하지 않기 떄문에 error
                    super();
                }catch(IOException ie){
                    ie.printStackTrace();
                }
            }
        }
        ```
    * 정상 코드

        ```java
        public class test2 extends test {

            public test2() throws IOException{ //자식 class에서도 동일하게 예외를 던져준다.
                      
                }
            }
        }
        ```

* NetWork를 다 배우고 나면 소켓을 이용한 채팅프로그램을 만들어 볼까??(프로젝트가 시작되서 시간적 여유가 있다면!)
### 내일 할일 

1. 메모장 다듬고 github에 올리기

2. 프로젝트 study하기

3. 머리자르기

