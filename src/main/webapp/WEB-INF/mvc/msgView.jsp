<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<spring:message code="hello"/><br> <!-- code는 properties에 있는 왼쪽 녀석들 -->
	<!-- application에 messageSOurce에 새롭게 추가 -->
	
	<!-- select box를 변경(change) 헤당 언어로 locale 변경
		<fmt:setLocale "en" />  ==> jsp방법
		spring은 interceptor, localeResolver(Session,Cookie를 주로 사용)를 이용해서 구현
	 -->
	<form> <!-- action 태그를 따로 작성해 주지 않으면 현재 page로 다시 보낸다 -->
		<select id="langSelect" name="language">
			<option value="ko">한국어</option>
			<option value="en">영어</option>
			<option value="ja">일본어</option>
		</select><br>
		<input type="submit" value="전송">
	</form>
	
</body>
</html>