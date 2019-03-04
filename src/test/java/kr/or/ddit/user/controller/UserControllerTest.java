package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends WebTestConfig{

	private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	
	/**
	* Method : testUserAllList
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : 사용자 전체 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> ulist = (List<UserVO>) mav.getModel().get("ulist");
		/***Then***/
		
		logger.debug("viewName : {}",viewName);
		
		assertEquals("/user/userAllList", viewName);
		assertNotNull(ulist);
		assertTrue(ulist.size()>100);
		
	}

}
