<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/css/dashboard.css" rel="stylesheet">

  </head>

  <body>
     <%@ include file="/WEB-INF/module/header.jsp" %>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
           <%@ include file="/WEB-INF/module/left.jsp" %> 
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">사용자 정보 조회</h1>
          
          <form id="frm" method="post" action="${pageContext.request.contextPath }/user/userForm" class="form-horizontal" role="form" enctype="multipart/form-data">
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">사진</label>
               <div class="col-sm-10">
                   <input type="file" class="form-control" id="profile" name="profile" placeholder="사진">
               </div>
            </div>
            
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" id="userId" name="userId" placeholder="사용자 아이디">
               </div>
            </div>
   
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="userNm" name="userNm" placeholder="사용자이름">
               </div>
            </div>
            
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">별명</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="alias" name="alias" placeholder="별명">
               </div>
            </div>
            
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">주소1</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly>
               </div>
            </div>
            
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">주소2</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소">
               </div>
            </div>
            
            <div class="form-group">
               <label for="userNm" class="col-sm-2 control-label">우편번호</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" readonly>
               </div>
               <div class="col-sm-offset-2 col-sm-3">
                  <button type="button" id="zipcodeBtn" class="btn btn-default">검색</button>
               </div>
            </div>
            
            <div class="form-group">
               <label for="pass" class="col-sm-2 control-label">Password</label>
               <div class="col-sm-10">
                  <input type="password" class="form-control" id="psss" name="pass" placeholder="*****">
               </div>
            </div>
            
   
            <div class="form-group">
               <div class="col-sm-offset-3 col-sm-9">
                  <button id="regBtn" type="button" class="btn btn-default">사용자 등록</button>
               </div>
            </div>
         </form>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
	
		function initData(){
			$("#userId").val("ljw");
			$("#userNm").val("ijdsf");
			$("#alias").val("jhw");
			$("#addr1").val("머전");
			$("#addr2").val("2층");
			$("#zipcode").val("32512");
			$("#pass").val("1234");
		}	
	
		$(document).ready(function(){ // 프로그램 시작시 실행(ready때문에)
			
			// 데이터 입력 초기화
			initData();
			
			if("${msg}" != "")
				alert("${msg}");
			
			/*
			if("중복 체크 실패" != "")
				alert("중복 체크 실패");
			*/
			
			//sever side에서 비교
			<c:if test="${request.msg != null}">
			alert("${request.msg}")
			</c:if>
		
			// 우편번호 검색 버튼 클릭 이벤트
			$("#zipcodeBtn").on("click",function(){
			    new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			            console.log(data);
			            
			            // 새 우편번호 : data.zonecode
			            // 우편번호 input select.val(data.zonecode);
			            $("#zipcode").val(data.zonecode);
			            
			            // 기본주소(도로주소) : data.roadAddress
			            // 주소1 input select .val(data.roadAddress);
			            $("#addr1").val(data.roadAddress);
			            
			            // 상세주소 input focus
			            $("#addr2").focus();
			        }
			    }).open();
			});
			
			// 사용자 등록 버튼 클릭 이벤트
			$("#regBtn").on("click",function(){
				// 사용자아이디 trim()은 공백
				if($("#userId").val.trim()==""){
					alert("사용자 아이디를 입력해주세요");
					$("#userId").focus();
					return false;
				}
				
				// 사용자 이름
				if($("#userNm").val.trim()==""){
					alert("사용자 이름을 입력해주세요");
					$("#userNM").focus();
					return false;
				}
				
				// 별명
				if($("#alias").val.trim()==""){
					alert("사용자 이름을 입력해주세요");
					$("#alias").focus();
					return false;
				}
				
				// 주소1
				if($("#addr1").val.trim()==""){
					alert("주소를 입력해주세요");
					$("#zipcodeBtn").trigger("click");
					return false;
				}
				
				// 주소2
				if($("#addr2").val.trim()==""){
					alert("상세주소를 입력해주세요");
					$("#addr2").focus();
					return false;
				}
				
				// 우편번호(생략)
				
				// 비밀번호
				if($("#pass").val.trim()==""){
					alert("비밀번호를 입력해주세요");
					$("#pass").focus();
					return false;
				}
				
				// 정상적으로 valisdation이 완료 --> form전송
				$("#frm").submit();
			});
		});
		
	</script>
	
  </body>
</html>

