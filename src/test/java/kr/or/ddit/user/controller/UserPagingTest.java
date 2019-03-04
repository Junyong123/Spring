package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;

public class UserPagingTest extends WebTestConfig{

	@Test
	public void UserPagingTesttest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcresult = mockMvc.perform(get("/user/userPagingList")).andReturn();
		ModelAndView mvc = mvcresult.getModelAndView();
		String viewname = mvc.getViewName();

		/***Then***/
		assertEquals("/user/userPagingList", viewname);

	}

}
