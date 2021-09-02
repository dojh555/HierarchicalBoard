# **목차**

[1\. 목적](#1-목적)  
[2\. 개요](#2-개요)  
[3\. 개발환경](#3-개발환경)  
[4\. 설계](#4-설계)  
[5\. spring 구조](#5-spring-구조)  
[6\. 기능 설명](#6-기능-설명)  
[7\. 후기](#7-후기)

---

## [1\. 목적](#목차)

-   계층형 게시판 이해와 구현
-   STS 툴을 이용해 spring framework 사용
-   MVC 패턴 활용
-   Tiles를 이용한 동적 레이아웃 구성
-   myBatis와 Oracle DB 이용한 DB 연동 및 조작

---

## **[2\. 개요](#목차)**
#### 1) 수행기간  
2021.08.09 ~ 2021.08.22

#### 2) 회원 기능

-   회원가입이 가능하다.
-   로그인이 가능하다.
-   인터셉터를 통해 비로그인 상태에서 게시물 작성시 로그인 화면을 요청한다.

#### 3) 계층식 답변형 게시판 기능

-   원글에 대한 답글을 달 수 있다.
-   원글은 최신순, 답글은 1차로 계층순 2차로 최신순으로 정렬한다.
-   메인 게시판 페이지는 페이징 기능을 구현한다.

#### 4) 게시물에 댓글 기능

-   원글, 답글등에 간단한 댓글 작성할 수 있다.
-   댓글 일정개수 이상일 시 더보기 기능으로 댓글을 볼수 있다.

---

## **[3\. 개발환경](#목차)**

-   Windows 10
-   JAVA JDK 1.8.0\_40
-   STS 3.9.15
-   Springframework 4.3.13
-   Tomcat 8.0
-   OracleXE11 - ojdbc6
-   mybatis 3.2.8
-   tiles-jsp 2.2.2
-   jackson-databind 2.9.2

---

## **[4\. 설계](#목차)**

#### 1) ERD ( ERMaster 사용 )

member: 회원 테이블  
board: 게시물 테이블  
reply: 댓글 테이블  

![image](https://user-images.githubusercontent.com/84883330/131491187-0749db37-bbce-46b7-b4b2-01d1a8a757d6.png)

#### 2) UML ( startUML 사용 )

2-1) Package Diagram

![image](https://user-images.githubusercontent.com/84883330/131491196-bb530709-5d4b-45fd-b8de-034b31070a7b.png)

2-2) Class Diagram (Business Layer, Presentation Layer)

-   **member**

![image](https://user-images.githubusercontent.com/84883330/131491210-0f72d587-08f0-41c4-928b-79df80b584c8.png)

-   **board, reply**

![image](https://user-images.githubusercontent.com/84883330/131491234-84cfb135-639c-400c-9ae0-74ff170ea345.png)

---

## **[5\. spring 구조](#목차)**

![image](https://user-images.githubusercontent.com/84883330/131491275-a2ff1bf5-57d4-4c03-b39b-4902c0159bd2.png)

---

## **[6\. 기능 설명](#목차)**

-   ****Tiles 구현****

![image](https://user-images.githubusercontent.com/84883330/131491312-05a1d647-5135-403f-99e1-d9975b65956a.png)

<br/><br/><br/>

-   ****회원 가입****

![image](https://user-images.githubusercontent.com/84883330/131491330-62edd2c3-b59b-418f-a9e9-52284a18c902.png)

<table style="border-collapse: collapse; width: 69.1842%; height: 50px;" border="1" width="219" data-ke-align="alignLeft" data-ke-style="style3"><tbody><tr style="height: 10px;"><td style="width: 576.969px; text-align: center; height: 10px;" colspan="2"><span style="color: #000000;"><b>DEVELOP Description</b><br></span></td></tr><tr style="height: 10px;"><td style="width: 15.2969px; height: 10px;" height="10px"><span style="color: #000000;">1</span></td><td style="width: 544.672px; height: 10px;"><span style="color: #000000;">Ajax<span>&nbsp;</span>로<span>&nbsp;</span>Dataservice layer<span>&nbsp;</span>의 데이터로<span>&nbsp;</span>ID<span>&nbsp;</span>중복체크</span></td></tr><tr style="height: 10px;"><td style="width: 15.2969px; height: 10px;" height="10px"><span style="color: #000000;">2</span></td><td style="width: 544.672px; height: 10px;"><span style="color: #000000;">다음 우편번호<span>&nbsp;</span>API<span>&nbsp;</span>를 사용하여 우편번호 검색<span>&nbsp;</span></span><span style="color: #000000;">가능</span></td></tr><tr style="height: 10px;"><td style="width: 15.2969px; height: 10px;" height="10px"><span style="color: #000000;">3</span></td><td style="width: 544.672px; height: 10px;"><span style="color: #000000;">Js<span>&nbsp;</span>를 통해 회원가입 유효성 검사</span></td></tr></tbody></table>
<br/><br/><br/>

-   ****로그인****

![image](https://user-images.githubusercontent.com/84883330/131491358-dd0251b9-9779-435c-b68a-339e781fc1db.png)

<table style="border-collapse: collapse; width: 69.1842%; height: 50px;" border="1" width="219" data-ke-align="alignLeft" data-ke-style="style3"><tbody><tr><td style="width: 99.8316%; text-align: center; height: 10px;" colspan="2"><span style="color: #000000;"><b>DEVELOP Description</b><br></span></td></tr><tr><td style="width: 5.38721%; height: 10px;" height="10px"><span style="color: #000000;">1</span></td><td style="width: 94.4444%; height: 10px;"><span><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용하여 </span><span style="color: #000000;">id,pw </span><span style="color: #000000;">일치하는 </span><span style="color: #000000;">회원 확인</span><span style="color: #000000;"></span></span></td></tr></tbody></table>
<br/><br/><br/>

-   ****게시판 목록****

![image](https://user-images.githubusercontent.com/84883330/131491400-277cbd3a-a881-4eed-bee9-9a75f505c4c1.png)

<table style="border-collapse: collapse; width: 68.3177%; height: 70px;" border="1" width="219" data-ke-align="alignLeft" data-ke-style="style3"><tbody><tr style="height: 10px;"><td style="height: 10px; text-align: center; width: 99.8296%;" colspan="2"><span style="color: #000000;"><b>DEVELOP Description</b><br></span></td></tr><tr style="height: 10px;"><td style="height: 10px; width: 5.62181%;" height="10px"><span style="color: #000000;">1</span></td><td style="height: 10px; width: 94.2078%;"><span style="color: #000000;">계층형 게시판 리스트</span></td></tr><tr style="height: 10px;"><td style="height: 10px; width: 5.62181%;"><span style="color: #000000;">2</span></td><td style="height: 10px; width: 94.2078%;"><span><span style="color: #000000;"><span style="color: #000000;">Paging<span>&nbsp;</span></span><span style="color: #000000;">기능</span></span></span></td></tr><tr style="height: 10px;"><td style="height: 10px; width: 5.62181%;"><span style="color: #000000;">3</span></td><td style="height: 10px; width: 94.2078%;"><span><span style="color: #000000;">비로그인 시<span>&nbsp;</span></span><span style="color: #000000;">interceptor<span>&nbsp;</span></span></span><span><span style="color: #000000;"><span style="color: #000000;">를 통해</span><span style="color: #000000;"><span>&nbsp;</span></span><span style="color: #000000;">로그인 화면으로 요청 변경</span></span></span></td></tr></tbody></table>
<br/><br/><br/>

-   ****댓글 등록, 삭제, 수정****

![image](https://user-images.githubusercontent.com/84883330/131491430-68ee074a-ba95-4c45-a626-54a92935a838.png)

<table style="border-collapse: collapse; width: 68.3177%; height: 40px;" border="1" width="219" data-ke-align="alignLeft" data-ke-style="style3"><tbody><tr style="height: 10px;"><td style="width: 99.8296%; height: 10px; text-align: center;" colspan="2"><span style="color: #000000;"><b>DEVELOP Description</b><br></span></td></tr><tr style="height: 10px;"><td style="width: 5.62181%; height: 10px;" height="10px"><span style="color: #000000;">1</span></td><td style="width: 94.2078%; height: 10px;"><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용해 비동기로 댓글등록</span></td></tr><tr style="height: 10px;"><td style="width: 5.62181%; height: 10px;"><span style="color: #000000;">2</span></td><td style="width: 94.2078%; height: 10px;"><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용해</span><span style="color: #000000;"><span>&nbsp;</span></span><span style="color: #000000;">비동기로 댓글삭제</span><span style="color: #000000;">/</span><span style="color: #000000;">수정 가능</span></td></tr></tbody></table>
<br/><br/><br/>

-   ****댓글 더보기, 접기****

![image](https://user-images.githubusercontent.com/84883330/131491457-0c6290c4-73fe-4e55-98e9-3913d06626c9.png)

<table style="border-collapse: collapse; width: 68.3177%; height: 70px;" border="1" width="219" data-ke-align="alignLeft" data-ke-style="style3"><tbody><tr style="height: 10px;"><td style="width: 99.8296%; height: 10px; text-align: center;" colspan="2"><span style="color: #000000;"><b>DEVELOP Description</b><br></span></td></tr><tr style="height: 10px;"><td style="width: 5.62181%; height: 10px;" height="10px"><span style="color: #000000;">1</span></td><td style="width: 94.2078%; height: 10px;"><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용해 댓글 </span><span style="color: #000000;">10</span><span style="color: #000000;">개 이상일 때</span><span style="color: #000000;"> </span><span style="color: #000000;">댓글 더보기 버튼 활성화<br></span><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용해 댓글 더보기 기능</span></td></tr><tr style="height: 10px;"><td style="width: 5.62181%; height: 10px;"><span style="color: #000000;">2</span></td><td style="width: 94.2078%; height: 10px;"><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용해 마지막 댓글일 때</span><span style="color: #000000;"> </span><span style="color: #000000;">댓글 접기 버튼 활성화<br></span><span style="color: #000000;">Ajax</span><span style="color: #000000;">를 이용해 댓글 접기 기능</span></td></tr></tbody></table>
<br/><br/><br/>

---

## **[7\. 후기](#목차)**

#### **느낀 점 😁**

댓글 기능으로 ajax 를 많이 사용하게 되었는데, 기존에 알고 있던 이론이 많이 부족하고 잘못된 부분이 있었다는걸 알게 되었다.
@ResponseBody와@RequestBody를 숙지하지 못하고 매번404, 415에러페이지를 겪었는데,  
이번 프로젝트를 통해 컨트롤러에서 리턴값의 기본은 뷰이름이며 뷰이름이 아닌JSON객체를 보내기 위해@ResponseBody를 사용한다는 것,  
@RequestBody는 보내온data가 쿼리스트링이면 당연히JSON형식의 데이터가 아니므로 맞지 않는 데이터 형식이라는 에러페이지가 뜬다는 것등을 알 수 있었다.

초기에 다른 프로젝트에서 spring 을 어노테이션 형식으로 구현했었는데 이번 프로젝트에서는 xml 형식으로 진행했다. 이를 통해 두 방식의 사용법과 서로 어느 부분이 동일한 설정으로 매칭되는지 알아볼 수 있었던 유익한 시간이었다.

#### **개선해야 할 점 🐱‍👓**

1\. 기본 이론을 숙지하자  
제대로 알지 못하고 사용하는 기능은 더 많은 시간을 할애하게 한다.  
모르는 부분은 애매하게 넘기지 말아야 한다.  
ajax 부분, spring 을 xml 형식으로 설정함에 있어서 경로 설정등에 어려움을 많이 겪었다.

2\. UML 다이어그램등을 통해 설계도를 잘 활용하자  
초기에는 UML 다이어그램을 통해 정리하여 쉽고 빠르게 파악할 수 있었지만 ,  
점차 필요한 기능이 추가되가면서 머리속에서만 설계를 마치는 경우가 있었다.  
결국 추후에 이 컨트롤이 어떤 기능을 하는지 다시 파악해야 했고 같은 기능을 중복하여 사용하는 비효율적인 부분도 발견할 수 있었다.
