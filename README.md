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
+ Javascript

<br>

## 3) ERD 설계 
![CCS_ERD](/img/CCS_ERD.jpg)
<br>
+ 다이어그램
![CCS_Diagram](/img/CCS_Diagram.png)
<br>

+ 사내 서비스라 로그인을 하지 않으면 서비스를 이용할 수 없게끔 기능을 구현하였습니다. 

<br>

## 4) 핵심 기능
+ 이 서비스의 특징은 간단한 CRUD시스템 위에 다양한 핵심 기능을 추가하였습니다.<br>
+ CCS의 핵심 기능은 출퇴근 관리와 결재 시스템입니다.<br>
+ 사용자(사원)는 간편하고 직관적인 UI를 통해 따로 보고할 필요없이 단지 몇번의 클릭만으로 출퇴근을 기록하며, 결재 요청을 할 수 있습니다.
+ 그리고 게시판을 추가해 사원들끼리 소통할 수 있는 공간도 마련하였습니다. 
![버튼](https://user-images.githubusercontent.com/86466976/132124207-9afb3550-5330-43a0-a2c4-566a375cc78a.gif)
<br><br/>
+ 또한 관리자 페이지를 만들어 관리자 계정으로 로그인하면 사용할 수 있는 사원 정보와 출퇴근 관리, 결재 승인/비승인 등등의 기능을 추가하였습니다.    
![관리자 메인](https://user-images.githubusercontent.com/86466976/132124550-2b8875ec-8c16-471c-8701-47b1e5e9f85b.gif)

<br>

### 4-1) 로그인 / 회원가입
<details>
<summary><b>로그인시 세션을 저장받아 모든 페이지에서 세션을 검사하는 로직</b></summary>
<div markdown="1">

```java
// 세션 검사
HttpSession session = request.getSession();
String idSession = (String)session.getAttribute("session_id");

if(idSession == null) {
  try {
    RequestDispatcher rd = request.getRequestDispatcher("/member/member_login_form.jsp");
    rd.forward(request, response);
  } catch(Exception e) {
    e.printStackTrace();
  }
}
```

+ 사내 서비스라 로그인을 하지 않으면 서비스를 이용할 수 없게끔 기능을 구현해야 했습니다. 
+ 따라서 위의 로직을 통해 세션을 검사하도록 해서 세션값이 존재하지 않으면 로그인 화면으로 돌아가도록 구성하였습니다.
</div>
</details>  
<br>
<details>
<summary><b>회원가입시 비밀번호 일치여부 검사 및 조건 설정</b></summary>
<div markdown="1">   

![비밀번호 검사](https://user-images.githubusercontent.com/86466976/132125912-1f412572-0bd7-4573-b32c-58748d0b6f3e.gif)
  
<br>
  
+ __아래는 비밀번호는 6글자 이상, 16글자 이하로 / 특수문자 필수 / 비밀번호 일치여부__ 이 3가지 기능을 구현해주는 로직입니다. 
  
```java
<script>
function check_pw(){
		
    var pw = document.getElementById('mpw').value;
    var SC = ["!","@","#","$","*"];
    var check_SC = 0;

    if(pw.length < 6 || pw.length > 16){
        window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
        document.getElementById('mpw').value='';
    }
    for(var i=0;i<SC.length;i++){
        if(pw.indexOf(SC[i]) != -1){
            check_SC = 1;
        }
    }
    if(check_SC == 0){
        window.alert('!,@,#,$,* 의 특수문자가 들어가 있지 않습니다.')
        document.getElementById('mpw').value='';
    }
    if(document.getElementById('mpw').value !='' && document.getElementById('repw').value!=''){
        if(document.getElementById('mpw').value==document.getElementById('repw').value){
            document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
            document.getElementById('check').style.color='blue';
        }
        else{
            document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('check').style.color='red';
        }
    }
}
</script>
```
</div>
</details>

<br>

### 4-2) 메인페이지
<details>
<summary><b>출퇴근 체크</b></summary>
<div markdown="1">   

+ 로그인을 하면 바로 접속되는 화면으로, 출근하기 버튼을 누르면 출근 시간이 기록되고 퇴근하기 버튼이 뜹니다.
+ 이후 퇴근하기 버튼을 누르면 퇴근 시간도 기록되고 버튼이 사라지며 다음 날이 되면 출근하기 버튼이 생기도록 기능 구현하였습니다. 
  

</div>
</details>


<br>

### 4-3) 게시판

![게시판 글쓰기](https://user-images.githubusercontent.com/86466976/132120612-0e909500-079d-45d9-a3dc-f59204ba61c3.gif)

기본적인 CRUD, 게시물 글쓰기, 글 조회, 글 수정, 글 삭제 기능을 추가하였고, 게시물 검색 기능과 게시물 필터를 적용 / 모든 게시물 리스트에 페이징 기능을 추가 / 게시물을 클릭하면 자신이 올린 게시물만 수정, 삭제하기 버튼이 보이도록 구성 / 
게시물 필터는 최신순과 조회순으로 구성 / 게시물 검색은 제목에 포함된 키워드를 검색할 수 있도록 구성, 최신순으로 보이게 함 

<br>

### 4-4) 결재창 / 관리자 페이지

사원은 휴가, 반차, 월차, 연차에 대한 결제 요청을 남길 수 있다. 관리자는 관리자 페이지를 통해 결재에 대한 승인, 비승인 여부를 결정할 수 있다. 그리고 관리자 페이지에서는 직원 목록과 결제서류 현황을 조회할 수 있다. 
