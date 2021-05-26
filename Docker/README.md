DockerFile
===

## Intro

Dockerfile은 Docker 이미지를 설정하는 파일입니다. Dockerfile에 설정된 내용대로 이미지가 생성이 됩니다. 

<br>


## Syntax

Dockerfile은 다음과 같이 <code>명령 매개변수</code> 형식으로 작성을 하며 Dockerfile에서 주석을 사용하려면 #을 이용하여 작성을합니다. 명령어는 대소문자를 구분하지 않지만 대부분 대문자로 작성을 하게 됩니다.

```Dockerfile
# 주석
명령어 매개변수 

# ex
FROM scratch
```

Docker는 Dockerfile에 작성된 명령을 순서대로 처리합니다. 그리고 <b>Dockerfile에서 명령은 항상 FROM으로 시작해야합니다</b> FROM이 없거나 FROM 앞에 다른 명령이 있다면 이미지가 생성되지 않습니다.

<br>


## Build

Dockerfile을 이용하여 이미지를 생성할 때 <code>build</code>라는 명령어를 이용하여 이미지를 생성하게 됩니다. Dockerfile이 있는 곳에서 실행을 하게 되며 <code>-t</code> option(--tag)와 함께 사용을 하게 됩니다.

```
docker build -t 이미지명 .
docker build -t 이미지명/사용자명 .
```

<code>-t</code>를 이용하여 이미지명을 지정해줄 수 있으며 Docker Hub에 이미지를 올리려면 이미지 뒤에 <code>/</code>와 함께 사용자명을 붙이면 됩니다. 이미지 명을 지정을 하지 않아도 이미지 생성을 할 수 있으며 이미지 명을 지정 없이 이미지를 생성했을 경우 이미지를 사용하려면 이미지 ID를 이용하면 됩니다.

이미지 명 뒤에 오는 <code>.</code>는 현재 위치를 나타내는 경로를 의미합니다.

<br>

## .dockerignore

Dockerfile과 가은 디렉토리에 있는 모든 파일을 context라고 한다. 특히 이미지를 생성할 때 context를 모두 Docker 데몬에 전송하므로 필요없는 파일이 포함되지 않도록 주의해야 합니다.

context에서 파일이나 디렉토리를 제외하고 싶을 때는 Dockerfile과 같은 디렉토리 안에서 작성을 하게 되며 Docker는 Go언어로 작성되어 있기 때문에 파일 매칭도 Go언어의 규칙을 따르게 됩니다.

```.dockerignore
example/hello.txt
.git
.svn
,,,
```

특정 파일이나 디렉토리를 제외할 수 있으며 보통 *를 주로 사용합니다. 버전관리르 위한 .git 혹은 .svn과 같은 파일들도 제외해줍니다.

<Br>

## 명령

### FROM

FROM은 어떤 이미지를 기반으로 이미지를 생성할지 설정을 합니다. Dockerfile로 이미지를 생성할 때는 항상 기존이 있는 이미지를 기반으로 생성하기 때문에 FROM은 반드시 설정해야 합니다.

다음과 같이 이미지 이름을 설정하거나 이미지 이름과 태그를 함께 설정할 수도 있습니다. 이미지 이름만 작성을 하게되면 latest를 사용하게 됩니다.

```Dockerfile
FROM 이미지 명
# ex
FROM ubuntu

FROM 이미지 명:태그 명
# ex
FROM ubuntu:14.04
```

FROM에 사용되는 이미지가 local에 존재한다면 local에서 가져와 사용하며 없다면 Dokcer Hub에서 가져와 사용을 하게 됩니다.

```FROM절은 하나의 Dockerfile에서 여려개를 사용할 수 있으며 FROM을 두 개 이상 설정한 경우 이미지가 두 개 생성됩니다. <code>-t</code>를 이용하여 이미지 이름을 설정했다면 가장 마지막에 있는 FROM에 적용됩니다.```

<br>

### MAINTAINER

MAINTAINER는 이미지를 생성한 사람의 정보를 설정합니다. 형식은 자유이며 보통 이름과 이메일을 입력합니다. MAINTAINER는 생략이 가능합니다.

```Dockerfile
MAINTAINER 이름 <이메일>
```

<br>

### RUN

RUN은 FROM에서 설정한 이미지 위에서 스크립트 혹은 명령을 실행합니다. 여기서 RUN으로 실행한 결과가 새 이미지로 생성이 되고 실행 내역은 이미지의 히스토리에 기록이 됩니다.

#### 셸(/bin/sh)로 명령 실행하기

<code>RUN 명령</code>형식으로 작성을 하게 되며 셸 스크립트 구문을 사용할 수 있습니다. <b>FROM으로 설정한 이미지에 포함된 /bin/sh의 실행파일을 사용하게 되며 /bin/sh/ 실행파일이 없으면 사용할 수 없습니다.</b>

``` Dockerfile
RUN apt update
RUN apt-get install -y curl
,,,
```

#### 셸 없이 바로 실행하기

<code>RUN ["실행파일","매개변수1","매개변수2"</code> 형식입니다. 실행파일과 매개변수를 배열 형태로 설정을 하게되며 FROM으로 설정한 이미지의 /bin/sh 실행파일을 사용하지 않는 방식입니다. 셸 스크립트 문법이 인식되지 않으므로 셸 스크립트 문법과 관련된 문자를 그대로 실행파일에 넘겨줄 수 있습니다.

```Dockerfile
RUN ["apt","update"]
RUN ["apt-get","install","-y","curl"]
```

<code>RUN</code>으로 실행한 결과는 캐시되며 다음 빌드 때 재사용합니다. 캐시된 결과를 사용하고 싶지 않다면 Dockerfile을 build할 때 <code>--no-cache</code> option을 사용하면 됩니다.

#### -y
<code>RUN</code>을 이용하여 <code>apt-get</code>혹은 <code>wget</code>과 같은 명령어로 instaill하는 경우 그 과정에서 사용자에게 [Y/N]으로 물어보는 경우 그 상태에서 응답이 오기 전까지 blocking이 되기 때문에 다음으로 넘어가지지 않는다. 그렇기 때문에 자동으로 대답해주기 위한 option이다.


<br>

### CMD

CMD는 컨테이너가 시작되었을 때 스크립트 혹은 명령을 실행합니다. 즉 <code>docker run</code> 혹은 <code>docker start</code>로 컨테이너를 실행할 때 <code>CMD</code>가 실행됩니다. <code>CMD</code>는 Dockerfile에서 한 번만 사용을 할 수 있습니다.

주로 build된 파일을 실행 할 때 많이 사용됩니다.

#### 셸(/bin/sh)로 명령 실행하기

```Dockerfile
CMD /mail
```

#### 셸 없이 바로 실행하기

```Dockerfile
CMD ["/main"]

# 매개변수가 있는 경우
CMD ["mysqld", "--datadir=/var/lib/mysql", "--user=mysql"]
```

<br>

### EXPOSE

EXPOSE는 HOST와 연결할 포트 번호를 설정합니다. <code>docker run</code>명령의 <code>-p</code>(--expose) option과 동일합니다.

<code>EXPOSE 포트번호</code>형식을 사용합니다. EXPOSE 하나로 포트 번호를 두 개 이상 동시 설정할 수 있습니다.

<b>EXPOSE는 호스트와 연결만 할 뿐 외부에는 노출이 되지 않습니다.</b> 그렇기 때문에 포트를 외부에 노출하려면 이미지를 사용할 때 <code>docker run</code>명령의 <code>-p</code> option을 사용해야 합니다.

```Dockerfile
EXPOSE 포트번호

# ex
EXPOSE 80
EXPOSE 443

EXPOSE 80 443
```

<br>

### ENV

<code>ENV</code>는 환경 변수를 설정합니다. <code>ENV</code>로 설정한 환경변수는 <code>RUN,CMD</code>에 적용이 됩니다.

<code>ENV 환경변수 값</code>형식을 가지게 됩니다. <code>ENV</code>로 설정한 환경변수를 사용할 때에는 <code>$</code>를 이용하게 됩니다.

```Dockerfile
ENV 환경변수 값

# ex
ENV GOPATH /go
```

<br>

### ADD 

<code>ADD</code>는 파일을 이미지에 추가하는 명령어 입니다. 

```Dockerfile
ADD 복사할 파일 경로 이미지에서 파일이 위치할 경로
```

#### 복사할 파일의 경로

복사할 파일의 경로는 컨텍스트 아래를 기준으로 하며(현재 Dockerfile의 위치) <b>바깥의 파일,디렉터리나 절대 경로는 사용할 수 없습니다.</b>

```Dockerfile
# ex (현재 디렉토리보다 위로 올라갈 수 없음)
ADD ../ex.txt
```

복사할 파일의 경로에 파일 뿐만 아니라 디렉토리도 설정을 할 수 있습니다. 디렉토리를 지정하면 디렉토리 안에 있는 모든 파일을 복사해서 가져옵니다. 또한 와일드 카드를 사용하여 특정 파일만 복사할 수 있습니다.(디렉토리를 통체로 가져오는 것이 아닌 안에 있는 파일들만 복사를 해온다.) 

만약 디렉토리를 통체로 복사하고자 한다면 <code>RUN mkdir</code>을 이용하여 디렉토리를 만들고 안에있는 파일을 복사합니다.

```Dockerfile
# ex (와일드 카드를 이용하여 특정 확장자만 가져오는 것이 가능)
ADD ./*.txt
```

복사할 파일의 경로에는 인터넷에 있는 파일의 URL을 설정할 수 있습니다. 

```Dockerfile
# ex (인터넷에 있는 파일의 URL을 설정하여 가져올 수 있다.)
ADD http://test.com/ex
```

로컬에 있는 압축파일은 추가할 때 압축을 풀고 추가가 되지만 URL로 가져온 압축파일은 가져오게 되면 스스로 압축을 풀지 않기 때문에 먼저 압축 파일을 local로 가져와서 압축을 풀고 추가해준다.

#### 이미지 파일이 위치할 경로

이미지 파일이 위치할 경로는 <b>항상 절대 경로를 이용하여 설정하여야 합니다.</b> 그리고 마지막에 <code>/</code>로 끝나면 ```디렉토리가 생성되고 파일은 그 아래에 복사가 됩니다.```


```Dockerfile
# ex (root부터 usr/src/app/ex 라는 디렉토리를 만들고 그 안에 ex.txt를 복사)
Add ./ex.txt /usr/src/app/ex/
```

<br>

### COPY

COPY는 ADD와 크게 차이가 없다. 다만 차이점은 COPY는 압축 파일을 추가할 때 압축을 해제하지 않고 복사할 파일 경로에 URL을 사용할 수없다.

```Dockerfile
COPY 복사할 파일 경로 이미지에서 파일이 위치할 경로
```

<br>

### USER

<code>USER</code>USER는 명령을 실행할 사용자 계정을 설정합니다. <code>RUN,CMD</code>에 적용이 됩니다.

```Dockerfile
USER 계정사용자명

# ex
USER nobody
RUN touch /ex/ex.txt

USER root
RUN touch /hello.txt
```

처음에는 nobody계정으로 ex디렉토리 안에 ex.txt를 생성합니다. 그 다음 root 계정으로 hello.txt.파일을 생성합니다. (/ 에는 root 계정만 파일을 생성할 수 있습니다.)


<br>

### WORKDIR

<code>WORKDIR</code>는 RUN,CMD의 명령이 실행될 디렉토리를 설정하게 됩니다.

<code>WORKDIR</code>뒤에 오는 모든 <code>RUN,CMD</code>에 적용됩니다. 작업중 <code>WORKDIR</code>를 사용하여 중간에 디렉토리를 변경할 수 있습니다.

```Dockerfile
WORKDIR 경로

# ex
WORKDIR /usr/src/app
RUN touch hello.txt

USER root
WORKDIR /
RUN touch hello.txt
```

위의 결과로는 /usr/src/app에 hello.txt가 하나 생성되고 /에 hello.txt가 생성된다. (cd라고 생각하면 편할수도 있습니다.)
