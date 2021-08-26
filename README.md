# :pushpin: Company Commute System

> 사원들의 통근을 관리하고, 사원들끼리 소통할 수 있는 서비스

<br>

## 1) 제작 기간 & 참여 인원
+ 2021년 8월 16일 ~ 8월 30일 (2주)
+ 팀 프로젝트 (4명)

<br>

## 2) 사용 기술
+ Java 8
+ JSP
+ MySQL 8.0
+ Bootstrap 5.1.0

<br>

## 3) ERD 설계 
![CCS_ERD](/img/CCS_ERD.jpg)
<br>
+ 다이어그램
![CCS_Diagram](/img/CCS_Diagram.png)

<br>

## 4) 핵심 기능
이 서비스의 특징은 간단한 CRUD시스템 위에 다양한 핵심 기능을 추가하였습니다.  


<br>

__<대략적인 UI와 기능 설명>__

+ 로그인 / 회원가입

세션을 저장받아 모든 페이지에서 세션을 검사하는 로직 / 회원가입에서 비밀번호가 일치하는지 검사하는 로직 / 사내 서비스라 로그인을 하지 않으면 서비스를 이용할 수 없게끔 기능을 구현 

+ 메인화면 

로그인을 하면 바로 접속되는 화면 / 출근하기 버튼을 누르면 출근 시간이 기록되고, 퇴근하기 버튼이 뜬다. 이후 퇴근하기 버튼을 누르면 퇴근 시간도 기록되고 버튼이 사라지며 다음 날이 되면 출근하기 버튼이 생기도록 기능 구현 

+ 게시판

기본적인 CRUD, 게시물 글쓰기, 글 조회, 글 수정, 글 삭제 기능을 추가하였고, 게시물 검색 기능과 게시물 필터를 적용 / 모든 게시물 리스트에 페이징 기능을 추가 / 게시물을 클릭하면 자신이 올린 게시물만 수정, 삭제하기 버튼이 보이도록 구성 / 
게시물 필터는 최신순과 조회순으로 구성 / 게시물 검색은 제목에 포함된 키워드를 검색할 수 있도록 구성, 최신순으로 보이게 함 

+ 결재창 / 관리자 페이지

사원은 휴가, 반차, 월차, 연차에 대한 결제 요청을 남길 수 있다. 관리자는 관리자 페이지를 통해 결재에 대한 승인, 비승인 여부를 결정할 수 있다. 그리고 관리자 페이지에서는 직원 목록과 결제서류 현황을 조회할 수 있다. 

+ 내 정보 보기

내 사원 정보를 조회하고, 수정할 수 있도록 구성



<br>

## 5) 느낀점

+ 기상 :
<br> 
+ 지우 :
<br> 
+ 예슬 :
<br> 
+ 재현 :
<br> 

