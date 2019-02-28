package kr.or.ddit.login;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class LoginControllerTest extends WebTestConfig{

	/**
	* Method : testLoginView
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void testLoginView() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn(); // ModelAndView를 사용할 수 있게해줌
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName(); // view 화면

		/***Then***/
		assertEquals("/login/login", viewName);
				
	}
	
	/**
	* Method : testLoginProcess_success
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : 로그인 성공 테스트
	 * @throws Exception 
	*/
	@Test
	public void testLoginProcess_success() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult= mockMvc.perform(post("/login").param("userId","brown").param("pass","brown1234")).andReturn();

		// session userVO 세션값
		// main 이동할 화면
		MockHttpServletRequest req = mvcResult.getRequest();
		HttpSession session = req.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		// view name
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName =mav.getViewName();
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("브라운", userVO.getUserNm());
	}
	
	/**
	 * Method : testLoginProcess_success
	 * 작성자 : pc15
	 * 변경이력 :
	 * Method 설명 : 로그인 실패 테스트
	 * @throws Exception 
	 */
	@Test
	public void testLoginProcess_faild() throws Exception{
		MvcResult mvcResult= mockMvc.perform(post("/login").param("userId","brown").param("pass","brown12345")).andReturn();

		// session userVO 세션값
		// main 이동할 화면
		MockHttpServletRequest req = mvcResult.getRequest();
		HttpSession session = req.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		// view name
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName =mav.getViewName();
		/***Then***/
		assertEquals("/login/login", viewName);
		
	}

}
