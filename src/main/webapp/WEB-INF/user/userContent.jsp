<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
          <h1 class="page-header">사용자 정보 조회(tiles)</h1>
          	
          	<form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/user/userModifyForm" method="get">
          	
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사진</label>
					<div class="col-sm-10">
						<img src="${pageContext.request.contextPath }/user/profileImg?userId=${userVO.userId}">
					</div>
				</div>
				<input type="hidden" id="userId" name="userId" value="${userVO.userId }"/>
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
					<div class="col-sm-10">
						<label class="control-label">${userVO.userId }</label>
					</div>
				</div>
	
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
					<div class="col-sm-10">
						<label class="control-label">${userVO.userNm }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">별명</label>
					<div class="col-sm-10">
						<label class="control-label">---</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<label class="control-label">**********</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">등록일자</label>
					<div class="col-sm-10">
						<label class="control-label">
							<fmt:formatDate value="${userVO.reg_dt}" pattern="yyyy-MM-dd"/>
						</label>
						
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">주소1</label>
					<div class="col-sm-10">
						<label class="control-label">${userVO.addr1 }</label>
						</div>
				</div>
				
				<div class="form-group">
				<label for="pass" class="col-sm-2 control-label">주소2</label>
				<div class="col-sm-10">
					<label class="control-label">${userVO.addr2 }</label>
					</div>
				</div>
				
				<div class="form-group">
				<label for="pass" class="col-sm-2 control-label">우편번호</label>
				<div class="col-sm-10">
					<label class="control-label">${userVO.zipcode }</label>
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" id=modiftbtn class="btn btn-default">사용자 수정</button>
					</div>
				</div>
			</form>
	
<!-- 	<script> -->
// // 		$(document).ready(function(){
// // 			$("#modifybtn").on("click",function(){
// // 				$("#userModi").submit();
// // 			});
// // 		});
<!-- <!-- 	</script> --> -->
	
  </body>
</html>