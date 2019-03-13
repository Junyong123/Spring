<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1 class="page-header">전체 사용자 리스트(AjaxTiles)</h1>
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
		<tbody id="userListTbody">

		</tbody>
	</table>
	
	<form action="${pageContext.request.contextPath }/user/userForm"
		method="get">
		<button type="submit" class="btn btn-default">사용자 등록</button>
	</form>

	<nav style="text-align: center;">
		<ul id="pagination" class="pagination">

		</ul>
	</nav>
</div>

<script>
	// 사용자 배열을 이용하여 사용자 리스폰 HTML을 생성
	function makeUserList(userList) {
		var html ="";
		for(var i=0;i<userList.length;i++){
			var user = userList[i];			
			html += "<tr class='userTr' data-userid='"+user.userId+"'>";
			html += 	"<td></td>";
			html += 	"<td>"+user.userId+"</td>";
			html += 	"<td>"+user.userNm+"</td>";
			html += 	"<td></td>";
			html += 	"<td>"+user.regdt+"</td>";
			html += "</tr>";		
		}
		$("#userListTbody").html(html);
	}
	
	function makePagination(userCnt,pageSize,page){
		var lastPage = parseInt(userCnt/pageSize) + (userCnt%pageSize>0?1:0);
		var html = "";
		
		if(page==1){
			html += "<li class='disalbed'>";
			html += "	<a arai-label = 'Previous'>";
			html += "	<spanaria-hidden='true'>&laquo;</span>";
			html += "	</a>";
			html += "</li>";
		}else{
			html += "<li>";
// 			html += "	<a href='${ pageContext.servletContext.contextPath}/userPagingList'";
			html += "	<a href='javascript:getuserPageList(1)' aria-label='Previous'>";
			html += "		<span aria-hidden='true'>&laquo;</span>";
			html += "	</a>";
			html += "</li>";
		}
		for(var i=1;i<=lastPage;i++){
			var active ="";
			if(i==page){
				active="active";
			}
			html += "<li class'"+active+"'>";
			html += "	<a href='javascript:getUserPageList("+i+");'>"+i+"</a>";
			html += "<li>";
			
			$("#pagination").html(html);
		}

	}
	
	function getUserPagingListHtml(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjaxHtml",
			data : "page="+page,
			success : function(data){
				
				var htmlArr = data.split("=====seperator=====");
				console.log(htmlArr[1]);
				
				$("#userListTbody").html(htmlArr[0]);
				$("#pagination").html(htmlArr[1]);
				
				// 클릭 이벤트
				// html이 ajax 호출에 의해 정상적으로 생성된 이후 클릭 이벤트 핸들러를 등록
				// (success -> 사용자 html이 생성된 이후에 등록)
				$(".userTr").on("click", function() {
					console.log("userTr click");
					//클릭한 userTr태그의 userId 값을 출력
					// 				console.log($(this).children()[1].innerText);
					// 				console.log("data-userid : " + $(this).data("userid"));

					var userId = $(this).data("userid");

					// 1.document
//	 				document.location = "/user?userId=" + userId;

					// 2.form
					$("#userId").val(userId);
//	 				$("#frm").attr("action", "/user?작성"); //속성바꿀때 사용
					$("#frm").submit();
				
				});
			}
		});
		
	}
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function() {
			console.log("document ready");
			
			// ajax를 통한 html 생성시 이벤트 핸들러 등록 방법
			// 1.html이 ajax 호출에 의해 정상적으로 생성된 이후 클릭이벤트 핸들러를 등록
			// (success -> 사용자 html이 생성된 이후에 등록)
			
			// 2.이벤트 핸들러 대상을 변경(.userTr -> #userListTbody)
			// 동적으로 생성되는 html을 감싸는 영역에 이벤트를 등록
			// 단 on 옵션에서 감싸는 영역 안에 처리되어야할 selector를 명시
			// $(".userTr").on("click",".userTr", function()){}
			// --->
			// $("#userListTbody").on("click",".userTr", function(){}
			
			$("#userListTbody").on("click",".userTr", function() {
				// *******************************************
				// userListTbody안에 있는 userTr태그에 이벤트를 삽입
				// *******************************************
				
				console.log("userTr click");
				//클릭한 userTr태그의 userId 값을 출력
				// 				console.log($(this).children()[1].innerText);
				// 				console.log("data-userid : " + $(this).data("userid"));

				var userId = $(this).data("userid");

				// 1.document
// 				document.location = "/user?userId=" + userId;

				// 2.form
				$("#userId").val(userId);
// 				$("#frm").attr("action", "/user?작성"); //속성바꿀때 사용
				$("#frm").submit();
			
			});
			
			getUserPagingListHtml(1);
			// msg속성이 존재하면 alert 아니면 넘어가기
			<c:if test="${msg != null}">
				alert("${mag}");
				<%session.removeAttribute("msg");%>
			</c:if>
			
		});
	</script>
<form id="frm" action="<%=request.getContextPath()%>/user/user"
	method="get">
	<input type="hidden" id="userId" name="userId" />
</form>