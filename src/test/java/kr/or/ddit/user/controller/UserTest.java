package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class UserTest extends WebTestConfig{

	@Test
	public void UserPagingTesttest() throws Exception {
		/***Given***/
		MvcResult mvcresult = mockMvc.perform(get("/user/user").param("userId","brown")).andReturn();
		
		/***When***/
		ModelAndView mvc = mvcresult.getModelAndView();
		String viewname = mvc.getViewName();
		UserVO uservo = (UserVO) mvc.getModel().get("userVO");

		/***Then***/
		assertEquals("/user/user", viewname);
		assertNotNull(uservo);

	}
	
	/**
	* Method : testProfileImg
	* 작성자 : pc15
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록폼 요청
	*/
	@Test
	public void testProfileImg() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcresult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mvc = mvcresult.getModelAndView();
		String viewname = mvc.getViewName();

		/***Then***/
		assertEquals("/user/userForm", viewname);
		
	}

}
