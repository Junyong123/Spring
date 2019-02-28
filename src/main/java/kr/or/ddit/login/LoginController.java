package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {

	@Resource(name="userService")
	private IUserService userService;
	
	/**
	* Method : loginView
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 :
	*/
	@RequestMapping(path={"/login"},method={RequestMethod.GET}) // 서블릿에서 매핑같은 녀석 , method는 타입
	// 따로 method를 설정하지 않으면 모든 타입을 받는다(get,post 상관없이)
	public String loginView() {

		return "/login/login";
	}
	
	@RequestMapping(path={"/login"},method={RequestMethod.POST})
	public String loginProcess(UserVO userVO,HttpSession session){// 필요하면 매개변수로 추가
		
		// 서블릿에서 파라미터를 얻는 부분
		// 자동 바인딩을 이용
		// 사용자가 요청한 id에 해당하는 실제 데이터 베이스에 저장된 값
		UserVO dbUserVO = userService.selectUser(userVO.getUserId());
		
		// 정상로그인
		if(dbUserVO.getUserId().equals(userVO.getUserId()) 
				&& dbUserVO.getPass().equals(KISA_SHA256.encrypt(userVO.getPass()))){
			// 세션 필요
			session.setAttribute("userVO", dbUserVO);
			
			return "/main"; // return은 view를 리턴 즉 화면  -> jsp에서 request.getRequestDispatcher()역할
		}
		else
			return "/login/login";
		
	}
}
