GitHub
===

## Branch

---

## branch란?

    버전을 관리함에 있어 원하는 시점에서 분기하여 파일을 업데이트하거나 수정을 한 후
    변경사항을 합침으로 효율적인 파일관리를 위해서이다.

    깃으로 버전을 관리를 시작하면 기본적으로 master이라는 branch가 만들어진다.
    사용자가 commit를 할 때마다 master branch는 최신 커밋을 가리킨다.

    원하는 시점에서 분기하는 것을 branch라 하며 변경사항을 원래있는 master branch에 
    합치는 작업을 merge(병합)이라 한다.

---

## branch만들기

* 현재 파일의 상태 및 log

    *  manual이라는 파일안에 work.txt가 생성되었으며 각 work 마다 content1,content2,content3이 추가 되었다.

        <img src = https://user-images.githubusercontent.com/74294325/102693981-7eb53d80-4261-11eb-9335-afad98789ef6.JPG>

        
    * HEAD는 여러 branch중 현재 작업중인 branch를 보여준다.<br>
    (HEAD -> master는 현재 작업중인 branch가 master임을 이야기한다.)

* 새 branch 만들기

    * 현재 branch의 list확인하기 (git branch)

        <img src = https://user-images.githubusercontent.com/74294325/102694095-2599d980-4262-11eb-938e-bc475ff96316.JPG>

        * 현재 master branch만 존재한다.

    * 새로운 branch 만들기 (git branch branch명)

        <img src = https://user-images.githubusercontent.com/74294325/102694147-90e3ab80-4262-11eb-89e1-5bcc0b7ff941.JPG>

        * apple라는 branch를 추가하였다.

    * branch 추가 후 log

        <img src = https://user-images.githubusercontent.com/74294325/102694227-0780a900-4263-11eb-8824-520db2802ace.JPG>
 
        * HEAD -> master,apple : 현재 master branch에서 작업중이며 존재하는 2개의 branch를 보여준다.

    * branch를 추가 후 master branch에서 work.txt에 content4를 추가 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102694339-b91fda00-4263-11eb-9957-be1e11b3713a.JPG>
        
        * 제일 최신인 master work4 커밋은 master branch에만 적용되어있다.
            나머지 branch는 work3의 커밋 상태이다.

        * --oneline 옵션은 한 줄에 한 커밋씩 나타내 주는 옵션이다.

    * branch를 이동하여 work.txt의 내용을 확인하기 (checkout branch명)

        <img src = https://user-images.githubusercontent.com/74294325/102694437-6135a300-4264-11eb-879f-492d57c640e1.JPG>

        * apple로 branch를 옮겼더니 HEAD가 apple를 가리키고 있다.

        * cat 명령어로 work.txt를 확인해보니 content4가 적용되지 않았다.

        * `apple는 content4가 추가 되기전 시점에서 분할을 한것이기에 master work4의 영향이 없다.`

---

## branch 정보 확인하기
    
* 새로운 branch에 커밋하기

    * apple branch에서 work.txt에 apple content4 추가 후 apple.txt 생성해서 동일 내용 추가 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102694579-58919c80-4265-11eb-9988-da351845072a.JPG>

        * HEAD -> apple 현재 작업중인 branch는 apple
        * 커밋한 apple content4는 apple에만 적용 ms,google에는 적용되지 않는다.

    * 각 branch의 커밋 확인하기(--branches 옵션)

        <img src = https://user-images.githubusercontent.com/74294325/102694636-ab6b5400-4265-11eb-92bb-136c51fe9434.JPG>

        * HEAD -> apple 현재 작업중인 branch는 apple
        * 각 branch의 최신 커밋을 알 수 있다.

    * 각 branch의 커밋을 그래프로 확인하기(--graph 옵션)

        <img src = https://user-images.githubusercontent.com/74294325/102694685-09983700-4266-11eb-9379-92a21fddd3bf.JPG>

        * apple의 최신 커밋은 apple content4이고 apple content4의 부모 커밋은 work3입니다.

        * master의 최신 커밋은 master content4이고 master content4의 부모 커밋은 work3입니다.

    * branch사이의 차이점 알아보기(git log 기준branch..비교branch)

        * 기준branch를 기준으로 비교branch와 비교합니다.

        <img src = https://user-images.githubusercontent.com/74294325/102694817-d1ddbf00-4266-11eb-893f-5b2ccd447a04.JPG>

        * master에는 없고 apple에만 있는 커밋 apple contnet4를 보여준다.

---

## branch 병합하기(merge)


    각각 만들어진 branch에서 작업을 하다가 어느 시점에서 브랜치 작업을 마무리하고 기존 브랜치에 합해야 하는 상황이온다.
    이러한 상황에서 branch를 병합한다. 

---

* 서로 다른 파일 병합하기.

    * manual-2라는 directory를 만들고 wrok2.txt를 생성하여 master branch에서 1을 입력 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102705766-6deee100-42ce-11eb-8a71-8f031aa2b1d6.JPG>

    * o2 branch를 만들고 현재 master branch에서 master.txt 를 만들고 master 2를 입력 후 커밋

        <img src =https://user-images.githubusercontent.com/74294325/102705802-c45c1f80-42ce-11eb-9608-294a14d3de8c.JPG>

        * master work2는 o2 branch에서는 적용되지 않는다.

    * o2 branch로 이동하고 o2.txt를 만들어 o2 2를 입력 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102705832-1ef57b80-42cf-11eb-93db-55f37adfdcae.JPG>

    * 현재 branch상황 조회

        <img src = https://user-images.githubusercontent.com/74294325/102705855-4f3d1a00-42cf-11eb-82ac-2bd72092c311.JPG>

        * 부모커밋은 work1에서 master branch와 o2 branch가 나누어져 각각 파일이 생성되고 수정되었다.

    * master branch에 o2 branch 병합하기

        * master branch로 이동 후 o2 branch를 병합
        * merge 명령어를 사용하면 자동으로 vim이 실행되며 커밋메세지가 나온다.

        <img src = https://user-images.githubusercontent.com/74294325/102705926-35e89d80-42d0-11eb-8c5a-2af34ef9829e.JPG>

        * o2.txt가 master branch에 추가 되었으며 merge할 때 커밋메세지도 등록되어있다.

---

* 같은 문서의 다른 위치를 수정했을 때 병합하기

    * manual-3 라는 directory를 만들고 wrok3.txt를 생성하여 master branch에서 내용을 입력 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102706030-a6dc8500-42d1-11eb-8283-bee453ea4915.JPG>


        <img src =https://user-images.githubusercontent.com/74294325/102706058-de4b3180-42d1-11eb-9a49-beacb156feba.JPG>


        * 내용 사이의 공백은 수정 후 병합하기 위해 넣었습니다.
    
    * o2 branch를 만들고 master branch에서 공백에 master content2를 입력하여 커밋합니다.

        <img src =https://user-images.githubusercontent.com/74294325/102706080-2cf8cb80-42d2-11eb-8299-91dec32f14bf.JPG >

        <img src = https://user-images.githubusercontent.com/74294325/102706102-70ebd080-42d2-11eb-8f1e-7ac9c362b4a9.JPG>

        * master branch에서 작업한 것은 o2 branch에서 적용되지 않는다.

    * o2 branch로 이동하여 o2 content 2 를 입력 후 커밋


        <img src = https://user-images.githubusercontent.com/74294325/102706151-c6c07880-42d2-11eb-880c-aa826030c1c2.JPG>

        <br>

        <img src = https://user-images.githubusercontent.com/74294325/102706184-f5d6ea00-42d2-11eb-8596-752d8c784354.JPG>

    * master로 이동 후 o2 merge하기

        <img src =https://user-images.githubusercontent.com/74294325/102706220-46e6de00-42d3-11eb-8f32-1b76b64f672c.JPG>

        * 각 branch에서 작업한 내용들이 work.txt에 잘 합쳐짐을 볼수 있다.

---

* 같은 문서의 같은 위치를 수정했을 때 병합하기.

    * manual-4 라는 directory를 만들고 wrok4.txt를 생성하여 master branch에서 내용을 입력 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102706336-531f6b00-42d4-11eb-9872-1c56b74a4804.JPG>

        <img src = https://user-images.githubusercontent.com/74294325/102706355-7b0ece80-42d4-11eb-8106-2743c4a1758d.JPG>

        * 공백의 위치에서 각 branch별 다른 작업을 할 것이다.

    * o2 branch를 만들고 master branch에서 공백에 master content2를 입력 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102706388-d6d95780-42d4-11eb-8dff-2ea8fa868dfe.JPG>

        <img src = https://user-images.githubusercontent.com/74294325/102706392-e48edd00-42d4-11eb-9afd-bc849c865ac0.JPG>

    * o2로 이동하여 공백에 o2 content2를 입력 후 커밋

        <img src = https://user-images.githubusercontent.com/74294325/102706436-3b94b200-42d5-11eb-8aa2-e475d2d74b40.JPG>

    * master로 이동 후 o2를 merge

        <img src = https://user-images.githubusercontent.com/74294325/102706457-61ba5200-42d5-11eb-92ce-a56d69d92e83.JPG>

        * 같은 위치에 각각의 branch가 다른 작업을 하였기에 충돌이 난 것을 볼 수 있다.

        * conflict: 충돌발생

    * vim으로 work.txt확인하기

        <img src = https://user-images.githubusercontent.com/74294325/102706478-9dedb280-42d5-11eb-87aa-1f96f8f345ed.JPG>

        * vim내용

            * "<<<<HEAD"는 이후 내용은 현재 branch를 의미
            * ==========은 가로줄을 기준으로 위는 현재 branch내용 아래는 병합할 branch 작업내용
            * ">>>>"는 병합할 branch를 의미한다.

    * ">>>" , "<<<" , =====  지우고 스테이지에 올려서 커밋하기

        <img src =https://user-images.githubusercontent.com/74294325/102706718-a810b080-42d7-11eb-9367-33436eb3abf8.JPG>

---
* merge가 완료된 branch를 삭제하기

    * 위에서 merge가 끝나 o2 branch를 삭제합니다.

        <img src = https://user-images.githubusercontent.com/74294325/102706763-0342a300-42d8-11eb-8b8e-9b11524429af.JPG>

        * `삭제할 branch에서 branch삭제 작업을 하는 것이 아닌 기본 branch에서 작업`