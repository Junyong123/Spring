/**
 * 자바스크립트로 쿼리를 파싱
 */

/**
 * @param cookiName : 쿠키 이름으로 해당 쿠기 값을 조회하는 함수
 */
function getCookieValues(cookieName) {
	
	var cookieValue ="";
	var cookieArray = document.cookie.split("; ");
	
	for(var i=0 ; i< cookieArray.length ; i++ ){
		
		// userId = brown
		var cookie = cookieArray[i];
		if(cookieName == cookie.split("=")[0]){
			cookieValue = cookie.split("=")[1]; // cookieValue;
			break;
		} 
	}
	return cookieValue;
}

/**
 * @param cookieName
 * @param cookieValue
 * @param expires
 * 쿠키 생성 후 유효날짜 까지 설정
 */
function setCookie(cookieName, cookieValue, expires){
	// 현재 날짜를 기준으로 expires 날짜 만큼 유효한 cookie 생성
	// 쿠키 생성 방법 : document.cookie = " cookie 문자열 포맷 " ;
	// cookie 문자열 포맷 : cookieName = cookieValue; path=/; expires=gmtString
	var today = new Date();
	today.setDate( today.getDate() + parseInt(expires) );
	document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + today.toGMTString() ;
}

/**
 * @param cookieNmae
 * 쿠기에 유효날짜가 초과하여 쿠키를 삭제
 */
function deleteCookie(cookieName){
	var dt = new Date() // 날짜 설정 : 오늘 날짜
	dt.setDate(dt.getDate() - 1) // 하루전 날짜
	
	// toGMTString은 날짜 오후 오전 기준으 나라마다 모호해 확실한 기준을 설정하여 오전인지 오후인지 확인
	document.cookie = cookieName + "=; path=/ expires=" + dt.toGMTString(); 
}