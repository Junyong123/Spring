package kr.or.ddit.lprod;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.model.ProdVO;
import kr.or.ddit.test.WebTestConfig;

public class lprodTest extends WebTestConfig{

	@Test
	public void testLprodAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprodAllList")).andReturn(); 
		ModelAndView mav = mvcResult.getModelAndView();
		String viewname = mav.getViewName();
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModel().get("lprodList");
		/***Then***/
		assertEquals("/lprod/lprodAllList", viewname);
		assertNotNull(lprodList);

	}
	@Test
	public void testLprodPagingList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprodPagingList")).andReturn(); 
		ModelAndView mav = mvcResult.getModelAndView();
		String viewname = mav.getViewName();
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModel().get("lprodList");
		/***Then***/
		assertEquals("/lprod/lprodPagingList", viewname);
		assertNotNull(lprodList);
		
	}
	@Test
	public void testprodList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/prodList").param("lprod_gu","P301")).andReturn(); 
		ModelAndView mav = mvcResult.getModelAndView();
		String viewname = mav.getViewName();
		List<ProdVO> prodList = (List<ProdVO>) mav.getModel().get("prodList");
		/***Then***/
		assertEquals("/lprod/prodList", viewname);
		assertNotNull(prodList);
		
	}

}
