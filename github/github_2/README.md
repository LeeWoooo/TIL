GitHub
===

## 버전만들기

* 작업 트리

    - 파일 수정,저장 등의 작업을 하는 디렉토리로 작업 디렉토리라 한다.

* 스테이지

    - 버전으로 만들 파일이 대기하는 곳이다.
    - 스테이지 영역이라고 부르기도 한다.

* 저장소 

    - 스테이지 에서 대기하고 있던 파일들을 버전으로 만들어 저장하는 곳입니다.

* 버전 만들기 과정

    * 작업 트리에서 먼저 작업을 수행 후 버전으로 만들고 싶은 것을 스테이지에 저장합니다.<br>
    그 후 스테이지에 있던 파일을 저장소로 commit하는 것이 버전을 만드는 순서입니다.

## 버전관리

* 파일 생성 ~ 버전관리까지.

    * 깃의 초기상태를 확인 합니다.(git status)

        <img src =https://user-images.githubusercontent.com/74294325/101977966-c4d43500-3c94-11eb-9f0e-8638bffe5571.JPG >

        * On branch master : 현재 master 브랜치에 있습니다.
        * No commits yet : 아직 커밋한 파일이 없습니다.
        * nothing to commit: 현재 커밋할 파일이 없습니다.
    
    * 파일을 생성 후 다시 한번 상태를 확인합니다.

        <img src = https://user-images.githubusercontent.com/74294325/101978090-c81bf080-3c95-11eb-8a8f-b162c447f7ae.JPG>

        * untracked file : 한번도 버전관리하지 않은 파일
        
    * 작업트리에서 만든 파일을 스테이징 하기

        <img src = https://user-images.githubusercontent.com/74294325/101978151-48daec80-3c96-11eb-9a0f-c6c31dacf087.JPG>

        * git add (파일명)을 이용한다.
        * warning은 리눅스와 윈도우의 줄바꿈문자가 다르기 때문이다. (사용하는데는 지장이없음.)
        * 새로 추가된 파일은 new file:
        * 기존의 파일이 수정된 것은 modified:

    * 스테이지에 올라온 파일 커밋하기.

        <img src = https://user-images.githubusercontent.com/74294325/101978210-9fe0c180-3c96-11eb-9ab6-a27dea3670b8.JPG>

        * git commit -m "커밋메세지"를 이용하여 버전을 생성한다.
        * 커밋 메세지는 한글로 적어도 되지만 영어를 지향한다.

    * 버전 확인하기

        <img src = https://user-images.githubusercontent.com/74294325/101978255-e7674d80-3c96-11eb-9cc7-418212ab6596.JPG>

        * commit: 커밋 해시(커밋을 구별하는 아이디)
        * Author : 작성자
        * Date : 버전 생성날짜.
        * 커밋 메세지를 확인 할 수 있다.

---

## 변경 사항 확인하기

* git diff

    * 파일을 변경 후 git diff를 사용하면 변경 사항을 알 수 있다.

        <img src =https://user-images.githubusercontent.com/74294325/101978368-a885c780-3c97-11eb-9982-a88ad438c0a0.JPG>

---

## 방금 커밋한 메세지 수정하기

* git commit --amend

    * 명령어를 입력하면 기본 편집기가 열리면서 작성했던 커밋메세지가 나온다.
        
        <img src = https://user-images.githubusercontent.com/74294325/101978528-ef27f180-3c98-11eb-90f5-fe3e516bd3f3.JPG>

        * 여기서 수정 후 :wq로 저장해서 편집기를 종료한다.
    
    * 기존 '3'에서 커밋메세지가 바뀐 것을 확인할 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101978551-21395380-3c99-11eb-8702-577b2450fcfa.JPG>

--- 

## 작업 트리에서 수정한 파일 되돌리기.

* git checkout

    * 현재 hello.txt.의 상태

        <img src = https://user-images.githubusercontent.com/74294325/101978705-3b276600-3c9a-11eb-9edd-f8dafeb5cefa.JPG>

    * '3'을 three 로 변경 후 상태 확인

        <img src = https://user-images.githubusercontent.com/74294325/101978734-6ca03180-3c9a-11eb-9ef2-223404ac080a.JPG>

    * git checkout을 이용하여 되돌리기

        <img src = https://user-images.githubusercontent.com/74294325/101978744-8fcae100-3c9a-11eb-9a1a-143442b2a75c.JPG>

---

## 스테이징 되돌리기

* git reset HEAD 파일이름

    * 파일을 add로 스테이징 한 것을 되돌리기

    * 파일을 수정한 후 status로 상태 확인 후 reset HEAD로 되돌리기

        <img src = https://user-images.githubusercontent.com/74294325/101981150-29e85480-3cae-11eb-8412-7e286be9a302.JPG>

        hello.txt를 수정 후 add로 스테이징을 하였다. <br>
        상태를 확인하여 modified로 스테이징이 된 것을 확인하였다.<br>
        git reset HEAD 명령어로 add로 스테이징 하기 전 상태로 되돌렸다.

--- 

## 최신 커밋 되돌리기

* git reset HEAD^

    * 파일을 스테이징 하고 커밋까지 완료 했을 때 가장 마지막 커밋을 취소하는 방법

    * 현재 log

        <img src = https://user-images.githubusercontent.com/74294325/101981341-7da76d80-3caf-11eb-96e2-620618f5b97a.JPG>

    * 명령어를 이용하여 마지막 커밋 삭제

        <img src = https://user-images.githubusercontent.com/74294325/101981356-9dd72c80-3caf-11eb-8607-8fb3bb14cdfb.JPG>

        * 가장 마지막 커밋을 삭제하면 스테이징 하기 전 상태로 돌아온다.

    * git reset의 명령옵션

        * git reset --soft HEAD^ : 최근 커밋을 하기 전 상태로 작업트리를 되돌린다.
        * git reset --mixed HEAD^ : 최근 커밋과 스테이징을 하기 전 상태로 되돌린다.
        * git reset --hard HEAD^ : 최근 커밋과 스테이징, 파일 수정을 하기 전 상태로 되돌린다. ( 되돌린 내용은 복구할 수 없다.)

---

## 특정 커밋으로 되돌리기(되돌아간 이후 커밋은 삭제)

* git reset 커밋해시

    * `내가 지정한 버전으로 되돌아 간 후 그 버전 이후 커밋들은 삭제한다.`

    * 현재 log
        
        <img src = https://user-images.githubusercontent.com/74294325/101981681-00312c80-3cb2-11eb-8d71-9a19e2de8c57.JPG>

        * 현재 rev.txt에는 a b c d 가 입력되어있다.
    
    * 내가 지정한 지점으로 버전 돌리기

        <img src = https://user-images.githubusercontent.com/74294325/101981704-2060eb80-3cb2-11eb-8d82-ced9e67d9c73.JPG>

        * hard 옵션을 주어서 파일을 수정하기 전 버전으로 돌렸다.

---

## 특정 커밋으로 되돌리기(되돌아 간 이후 커밋은 삭제되지 않음.)

* git revert

    * reset과 다르게 특정 커밋을 하기 전 상태로 되돌린 후 그 이후 커밋은 삭제되지 않고 log로 남아있다.

    * reset은 돌아갈 커밋해시를 입력하지만 revert는 돌아갈 버전 다음의 커밋해쉬를 입력한다.

        * 즉 revert명령 뒤에 취소하려고 하는 버전의 커미해시를 입력한다.

    * revert 명령어를 입력하면 기본 편집기가 실행되며 주석을 첨부할 수 있다.

    * 현재 log (R5 커밋은 e를 추가함. 현재 rev.txt의 상태는 a b e 가 입력되어있다.

        <img src = https://user-images.githubusercontent.com/74294325/102003748-daf1fc00-3d4d-11eb-8ca2-56ca4fc6a2d8.JPG>

    * R5의 log를 revert명령어로 취소시킨다.

        <img src = https://user-images.githubusercontent.com/74294325/102003817-17bdf300-3d4e-11eb-8883-c3ed6b7e310c.JPG>

        * R5의 로그는 삭제된 것이 아니라 취소 된것이다. 다시 커밋해시로 돌아갈 수 있다.

    * revert 이후 rev.txt 상태

        <img src = https://user-images.githubusercontent.com/74294325/102003826-3de39300-3d4e-11eb-8883-40066eb02cd6.JPG>
