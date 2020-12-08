GitHub
===

## Linux 명령어 연습.

1. 현재 Directory 확인하기

    * pwd 명령어를 이용하여 확인 할 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101497467-5baba380-39ae-11eb-8a16-f57b87e4433d.png>

2. 현재 Directory에 있는 파일들 확인하기

    * ls 명령어를 이용하여 확인 할 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101497686-957caa00-39ae-11eb-8a55-29eb2970222f.png >

    * ls 명령 옵션

    옵션 | 설명
    :-- | :--
    -a | 숨긴 파일과 디렉토리도 함께 표시한다.
    -l | 파일이나 디렉토리의 상세 정보를 함께 표시한다.
    -r | 파일의 정렬 순서를 거꾸로 표시한다.
    -t | 파일 작성 시간 순으로 (내림차순) 표시합니다.

3. Directory 이동하기

    * cd 명령어를 이용하여 이동 할 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101498106-10de5b80-39af-11eb-8392-3119234651a0.png>


    * cd .. 은 상위 Directory 이동한다.

    * cd ~ 은 처음 출발했던 홈 Directory로 이동한다.

4. Directory 만들기 및 지우기

    * mkdir Directory명 명령어를 이용하여 만들수 있다.
  
    * rm -r Directory명 명령어로 지울 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101498801-dfb25b00-39af-11eb-858b-bc97b26be98b.png>

5. vim을 이용하여 txt파일 만들기.

    * vim 명령어를 이용하여 만든다.

        <img src = https://user-images.githubusercontent.com/74294325/101500288-9d8a1900-39b1-11eb-916a-68b28d36bbd8.png>

    * txt파일이 생성되고 하단에 "new"라는 문구가 표시된다.

        <img src = https://user-images.githubusercontent.com/74294325/101500416-cad6c700-39b1-11eb-9187-f6b0be638f17.png>

    * i를 입력하면 입력모드로 변환 후 text를 입력할 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101500479-e4780e80-39b1-11eb-9bdd-a8055fb59a5b.png>

    * esc를 입력하면 ex모드로 전환된다.

        명령 | 설명
        :-- | :--
        :w | 편집 중이던 문서를 저장합니다.
        :q | 편집기를 종료합니다.
        :wq (파일) | 편집 중이던 문서를 저장하고 종료합니다.
        파일 이름을 입력하면 그 이름으로 저장
        :q! | 문서를 저장하지 않고 종료합니다.

        <img src = https://user-images.githubusercontent.com/74294325/101500835-581a1b80-39b2-11eb-82d9-593ae02a8b93.png>

6. txt 문서 내용 확인하기

    * cat(concatenate) 명령어를 사용하여 확인할 수 있다.

        <img src = https://user-images.githubusercontent.com/74294325/101501411-09b94c80-39b3-11eb-9906-c34d0b94244e.png>