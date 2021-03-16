Connection Poll
===

## DBCP

커넥션 객체를 모아둔 개념을 커넥션 객체 생성과 삭제에 드는 오버해드를 방지하기 위한 객체 재활용 방식이다.

> 오버헤드 : 어떤 처리를 하기 위해 들어가는 간접적인 처리시간, 메모리 등을 말한다.

<br>

DB는 외부 리소스이기 때문에 커넥션을 생성하고 삭제하는 것에 오버해드가 발생한다. 또한 DNMS입장에서도 커넥션을 계속 맺고 끊어야 하기 때문에 오버헤드가 발생한다. <br>

**따라서 커넥션 객체를 일정량 모아둔 Pool을 생성해두고 계속 재활용을 하는 방식이다. (커넥션을 커넥션 풀에서 가져다 사용하고 반납하는 형식)** <br>

자원을 가져다 사용한 것이기에 반납은 필수적으로 해야하며 close()를 사용하게 된다.<br>

## 생성방법

커넥션 풀에 넣을 커넥션을 context.xml파일에 기입을 한다. 기입한 커넥션 정보는 웹 컨테이너가 구동되면서 미리 읽어온 뒤 저장하고 있는다. DB의 커넥션 정보를 입력하면 내부적인 로직을 통해 DB와 연동해 커넥션 풀을 생성한 뒤 DataSource객체를 만들어 보관한다. <br>

### context.xml 설정코드
```xml
<Resource
	auth="Container" 
	driverClassName="oracle.jdbc.OracleDriver"
	url="jdbc:oracle:thin:@localhost:1521:orcl"
	id="scott"
	pwd="tiger"
	name="jdbc/Oracle"//커넥션 풀의 이름(사용자가 설정이 가능하다)
	type="javax.sql.DataSource"//커넥션을 만들어주는 DataSource객체
	initialSize="5"
	minIdle="5"
	maxIdle="50" 
	maxActive="50"
	maxWait="10000"
/>
```

<br>

* auth : 사용하고 있는 컨테이너를 커넥션 풀로 사용하겠다는 인증절차.

* InitialSize : 최초 커넥션풀이 생성된 후 실제 DB와 연결되는 Connection 객체를 몇개 생성할지를 결정한다.

* minldle : Connection 객체가 유지될 최소 갯수, 만약 오류가 나서 연결이 끊어지면 해당 Connection객체가 제거되는데 이렇게 객체가 제거되다가 minIdle 설정값 이하로 떨어지면 다시 새로 만들어서 숫자를 채워준다.

* maxldle : minIdle 설정값은 최소 유지 갯수이다. 그것보다 더 많은 경우에 대해서는 관여하지 않는다. 만약 현재 5개의 Connection 객체가 생성돼 있는데 사용자 요청이 들어올 경우 추가적으로 생성해서 사용한다. 이 커넥션은 사용이 끝난 뒤 반납되어 유휴상태가 되는데 이 때 유지할 최대 갯수를 설정하는 값이다.  maxIdle이 10일 때 11번째부터 생성된 커넥션은 반납되면 자원이 해제됩니다.

* maxActive : 동시에 사용할 수 있는 Connection 객체의 최대 값이다. 이 값이 바로 커넥션 풀에서 가질 수 있는 최대 Connection객체의 갯수가 된다. 객체는 maxIdle 값보다 더 많이 생성될 수 있지만 maxActive 값보다 더 많이 생성될 수는 없다. maxIdle 값보다 더 많이 생성된 객체는 반납 후 해제된 다는 차이가 있다. 따라서 이 두개의 설정은 같은 값으로 맞춰주는 것이 일반적이다.

* maxWait : 커넥션풀에 Connection 객체를 요청했을 때 존재하는 커넥션이 모두 사용중이고 더 만들수도 없다면 잠시 대기하는 시간을 갖는다. 하지만 이 시간이 지나도 커넥션을 사용하지 못한다면 예외가 발생한다. (너무 길면 사용자가 오래 기다려야 하고 너무 짧으면 예외가 발생할 확률이 높아진다.)

<br>

### Context 객체 생성 : InitialContext class는 Context 인터페이스를 상속받았으므로 오버라이딩 해서 생성해준다.

```java
try{
	Context context = new InitialContext();
}catch(NamingException e){ //네이밍 예외
	e.printStackTrace();
}
```

<br>

### DataSource

해당 객체는 커넥션 풀을 관리해준다. Context 객체에서 메소드를 통해 Object타입으로 반환 받은 뒤 타입 캐스팅을 통해 변환해준다. (Object인 이유는 외부 리소스 종류에 따라 관리하는 class가 달라질 수 있기 때문이다.)<br>

"java:comp/env" 까지는 고정이고 그 뒤는 context.xml 파일에 기입한 정보중 name 값을 넣어주면 된다.
```java
DataSource dataSource;
try{
	Context context = new InitialContext();
	dataSource = (DataSource)context.lockup("java:comp/env/jdbc/Oracle");
}catch(NamingException e){ //네이밍 예외 입력한 자원의 이름이 없을 수도 있고 잘못 기입했을 수도 있기 때문
	e.printStackTrace();
}
```

<br>

### Connection 얻기

Connection은 DataSource의 객체에서 getConnection() method를 사용하여 얻어온다.

```java
Connection con = dataSource.getConnection();
```

<br>

코드정리
```java
DataSource dataSource = null;
try{
	Context context = new InitialContext();
	dataSource = context.lockup("java:comp/env/jdbc/Oracle");
}catch(NamingException e){
	e.printStackTrace();
}

try(Connection con = dataSource.getConnection()){

	//DB관련 작업

}catch(SQLException e){
	e.printStackTrace
}
```