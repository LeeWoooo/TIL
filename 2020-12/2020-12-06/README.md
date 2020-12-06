Today I Learned (TIL)
===

## 2020-12-06

---

### 오늘 한 일



1. SIST 팀 project 업무나누기 및 DeadLine 설정

    * DeadLine은 12월 9일로 정해졌다.

    * 과제는 총 8개의 주제로 된 과제이다.

    * 인당 2개씩 나눴지만 혼자서도 8개를 다 구현해볼 생각이다.

    * 1번과제 완료


### 오늘 느낀점, 배운점

* indexOf의 복습

    * String class의 indexOf

    * 매개변수의 종류로는 indexOf(String str), indexOf(String str, int formIndex)

        * indexOf(String str);
        ```java
        String msg = "Hello World";
        msg.indexOf("World"); //매개변수로 넣어준 문자열의 첫번째 글자의 index를 찾게 됩니다.
        msg.indexOf("H"); //매개변수로 넣어준 H에 해당하는 index를 찾게 됩니다.
        ```

        * indexOf(String str, int formIndex);
        ```java
        String msg = "Hello World";
        msg.indexOf("World",5); //msg의 5번 index뒤에서부터 World를 찾기 시작합니다. 
        ```

* HashMap의 복습

    * HashMap의 값들을 꺼내서 확인하기

    * 방법 1 (HashMap에 저장된 key-value값을 엔트리(키와 값을 결합)의 형태로 set에 저장하여 반환)

    ```java
    Set<Entry<String, String>> entrySet = map.entrySet();
    for (Entry<String, String> entry : entrySet) {
    System.out.println(String.format("키 : %s, 값 : %s", entry.getKey(), entry.getValue()));
    }
    ```

    * 방법2 (keySet을 이용한 for문)
    ```java
    for (String key : map){
        System.out.println("key="+key+"value="+map.get(key));
    }
    ```

    * 방법3 (Iterator을 이용한 while문)
    ```java
    Iterator<String> keys = map.keySet().iterator();
    while (keys.hasNext()) {
    String key = keys.next();
    System.out.println(String.format("키 : %s, 값 : %s", key, map.get(key)));
    }
    ```

* Map의 getOrDefault() method 발견!

    * getOrDefault() : 찾는 키가 존재하면 해당 키의 값을 반환하고, 없으면 기본 값을 반환한다.

    ```java
    String[] arr = {"a","b","c","a","b","a"};

    Map<String,Integer> map = new HashMap<String, Integer>();
    for(String key : arr){
        map.put(key,map.getOrDefault(key,0)+1); //key가 존재하면 key의 값을 반환 없으면 0을 반환
        //a=3,b=2,c=1
    }
    ```

* 배운 범위에서만 문제를 해결하려는 것도 좋지만 배운 범위에서 해결하는 것이 효율적이지 않다면 검색하여 빠르게 구현하고 이해하자.

* API를 보고 사용할 수 있는 method들을 이해하는 것이 필요하다.

### 내일 할일 

1. SIST 강의 듣기(network) + 정리 후 올리기

2. SIST 팀 Project 구현하기

3. 감사하기, 웃기 :)