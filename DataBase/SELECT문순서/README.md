SELECT query 실행 순서 
===

SQL 쿼리문을 작성할 때 사용되는 WHERE, GROUP BY, ORDER BY 절과 같은 구문을 **실행하는 순서**가 존재한다. 이 순서에 의해서 쿼리가 처리되고 어떻게 쿼리문을 작성하느냐에 따라 퍼포먼스 적인 차이가 발생한다 <br>


## 작성 순서

>**SELECT -> FROM -> WHERE -> GROUP BY -> HAVING -> ORDER BY**

하지만 작성한 순서대로 실행이 되는 것이 아니다.

<br>

## 실행 순서

>**FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY**

<br>

## 자세히


### FROM

쿼리의 가장 첫번째 실행 순서는 FROM절이다. FROM 절에서는 전체 테이블의 결과를 가지고 온다. INDEX를 사용하지 않는다는 가정에서 WHERE절이나 SELECT절에서 일부 행이나 열을 제거하여 출력한다고 해도 **가장 처음에 테이블의 모든 데이터를 가져온다.**

<br>

### WHERE

이 후 두번째로 WHERE절에서는 FROM절에서 읽어온 테이블에서 **조건에 맞는 결과만 가져오도록 데이터를 간추린다.**

<br>

### GROUP BY , HAVING

세번째는 WHERE절을 이용하여 간추린 데이터를 Group로 묶고 그 묶이는 Group에 대한 조건을 부여하는 GROUP BY절과 HAVING절을 작업하게 된다.

<br>

### SELECT 

Group으로 묶이고 간추려진 데이터를 받아 그 데이터에서 어떠한 열을 출력할지 결정을 한다.

<br>

### ORDER BY

마지막으로 행의 순서를 결정해서 정렬하는 것으로 실행이 종료된다.

<br>

---

<Br>

## 실행 순서가 중요한 이유

쿼리를 사용할 때 최종적으로 출력되는 결과만 확인할 수 있다. 각 단계에서 어떤 데티어를 읽고 사용할 수 있는지 실행 순서를 모르면 query를 작성하는데 불편하기 때문이다.

<br>

간단하게 예를 들자면 ALIAS를 사용할 때 SELECT에서 선언한 ALIAS는 SELECT 다음으로 실행되는 ORDER BY절 이외에서는 사용될 수 없다. (ALIAS를 WEERE절에서 사용할 수 없음.) 

<br>

또 한가지는 ROWNUM의 사용이다. SELECT절에서 ROWNUM으로 행의 번호들을 가져오지만 그 뒤에 ORDER BY가 있다면 그 정렬조건에 맞추어 다시 재정렬 되기 때문에 ROWNUM의 번호가 섞이게 된다.
