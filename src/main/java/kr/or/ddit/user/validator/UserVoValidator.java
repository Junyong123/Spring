package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVO;

public class UserVoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {

		return UserVO.class.isAssignableFrom(clazz);
		// 해당 vo 객체가 할당이 가능한지 확인
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 검증할 객체를 스프링이 넘겨주는 역할
		// target VO ,errors는 문제가 생겼을때 내용이 담겨지는 역할 
		UserVO userVO = (UserVO)target;
		
		// 비밀번호는 8자리 이상이어야 한다
		if(userVO.getPass().length()<8){
			errors.rejectValue("pass","passLen");
		}
		
		// 사용자 아이디 검증(빈값이면 안된다)
		if(userVO.getUserId().equals("")){
			errors.rejectValue("userId","required");
		}
		
		// 사용자 아이디는 6자리 이상이어야 합니다
		if(userVO.getUserId().length()<6){
			errors.rejectValue("userId","userIdlen");
		}
		
		
	}

}
