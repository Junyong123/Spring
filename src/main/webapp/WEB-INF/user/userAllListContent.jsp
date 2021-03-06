<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

   <h1 class="page-header">전체 사용자 리스트(tiles)</h1>
   	<!-- userList 정보를 화면에 출력하는 로직 작성 -->
   	<div class="table-responsive">
     <table class="table table-striped">
       <thead>
         <tr>
           <th>#</th>
           <th>사용자 아이디</th>
           <th>사용자 이름</th>
           <th>별명</th>
           <th>등록일시</th>
         </tr>
       </thead>
       <tbody>
         
         <c:forEach items="${ulist }" var ="user" varStatus="i">
         <%-- 향상된 for문은 begin이 없어서 i값을 구할 수 없음으로 varStatus를 이용해서 구한다 --%> 
         	<tr class='userTr' data-userid='${user.userId }'>
         		<td> ${i.index } </td>>
         		<td> ${user.userId }</td>
         		<td> ${user.userNm }</td>
         		<td> --- </td>
         		<td> <fmt:formatDate value="${vo.get_dt }" pattern="yyyy-MM-dd"/></td>
         	</tr>
        </c:forEach>
       </tbody>
     </table>
   </div>

	<script>
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function(){
			console.log("document ready");
			
			//사용자 tr 태그 클릭시 이벤트 핸들러
// 			$(".userTr").click(function(){
// 			});
			
			$(".userTr").on("click", function(){
				console.log("userTr click");
				//클릭한 userTr태그의 userId 값을 출력
// 				console.log($(this).children()[1].innerText);
// 				console.log("data-userid : " + $(this).data("userid"));
				
				var userId = $(this).data("userid");
				
				// 1.document
// 				document.location = "/user?userId=" + userId;
				
				// 2.form
				$("#userId").val(userId);
// 				$("#frm").attr("action", "/user"); //속성바꿀때 사용
				$("#frm").submit();
				
			});
			
		});
	</script>
	
	<%
		pageContext.getRequest().equals(request);
		pageContext.getSession().equals(session);
		
		// 1
		request.getContextPath();
		((HttpServletRequest)pageContext.getRequest()).getContextPath();
		application.getContextPath();
		
		// 2
		((HttpServletRequest)pageContext.getRequest()).getContextPath();
		pageContext.getServletContext().getContextPath();
		
		// el은 request나 context에 직접 접근이 불가능하다
	%>
	
	<form id ="frm" action="${pageContext.servletContext.contextPath }/user/user" method="get">
		<input type="hidden" id="userId" name="userId"/>
	</form>
