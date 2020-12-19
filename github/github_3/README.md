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








