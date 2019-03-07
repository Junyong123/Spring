<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
	<td>{
		border ='1';
	}
</style>

<script src="${cp }/js/jquery-3.3.1.min.js"></script>

<script>
	$(document).ready(function(){
		console.log("ajaxView.jsp");
		
		$("#jsonReqBtn").on("click",function(){
// 			jsonView();
// 			responserBody();
			requestBody();
		});
	});
	
function requestBody(){
	var data = {userId : "brown",userNm : "브라운"};
	$.ajax({
		url : "${cp}/ajax/requestBody",
		method : "post",
		dataType : "json", 
// 		data : "userId=brown&userNm=브라운", // 파라미터
		data : JSON.stringify(data), // data를 json 문자열로 전송한다.		
// 		data : $("#frm").serialize(), // form으로 올경우  태그안에 있는 녀석들을 문자열로 만들어줌 소스를 간단하게 만들어줌
		contentType : "application/json; charset=utf-8", // client 전송하는 데이터 타입
		success : function(data){
			console.log("data : "+data);
			
			$("#jsonRecvTbody").html("<tr><td>"+data.userId+"</tr></td>");
			
// 			var html="";
// 			// data.rangerList => array
// 			for(var i=0;i<data.rangerList.length;i++){
// 				var ranger = data[i];
// 				html += "<tr><td>"+ ranger + "</td></tr>"
// 			}
// 			$("#jsonRecvTbody").html(html);
		}
	});
}	
	
function responseBody(){
	$.ajax({
		url : "${cp}/ajax/responseBody",
		method : "post",
		dataType : "json", // server에게 희망하는 리턴타입을 명시(이런타입으로 올거라 예상)
		success : function(data){
			console.log("data : "+data);
			
			var html="";
			// data.rangerList => array
			for(var i=0;i<data.rangerList.length;i++){
				var ranger = data[i];
				html += "<tr><td>"+ ranger + "</td></tr>"
			}
			$("#jsonRecvTbody").html(html);
		}
	});
}

function jsonView(){
	$.ajax({
		url : "${cp}/ajax/jsonView",
		method : "post",
		success : function(data){
			console.log("data : "+data);
			
			var html="";
			// data.rangerList => array
			for(var i=0;i<data.rangerList.length;i++){
				var ranger = data.rangerList[i];
				html += "<tr><td>"+ ranger + "</td></tr>"
			}
			$("#jsonRecvTbody").html(html);
		}
	});
}
</script>


</head>
<body>
<form id="frm">
<!-- form으로 올경우에 방법 -->
	<input type="text" name ="userId" value="brown"/>
	<input type="text" name ="userNm" value="브라운"/>
</form>

	<h2>ajaxView.jsp</h2>
	<h3>수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData요청</button>
		<div id="jsonRecv">
			<table>
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id="jsonRecvTbody">
				
				</tbody>
			</table>
		</div>
		
	</div>
</body>
</html>