package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.model.ProdVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/lprod")
@Controller
public class lprodController {
	
	private Logger logger = LoggerFactory.getLogger(lprodController.class);
	
	@Resource(name="lprodService")
	public ILprodService lprodService;

	@RequestMapping(path="/lprodAllList",method=RequestMethod.GET)
	public String lprodAllList(Model model){
		
		List<LprodVO> lprodList = lprodService.getAllLprod();
		
		model.addAttribute("lprodList", lprodList);
		
		return "/lprod/lprodAllList";
	}
	
	@RequestMapping(path="/lprodPagingList",method=RequestMethod.GET)
	public String lprodPagingList(PageVO pageVO,Model model){
		
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVO);
		List<LprodVO> lprodList = (List<LprodVO>)resultMap.get("lprodList");
		int lprodCnt = (Integer)resultMap.get("lprodCnt");
		int lastPage = (Integer)(lprodCnt/pageVO.getPageSize() + (lprodCnt%pageVO.getPageSize() > 0 ? 1 : 0));
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("lprodCnt", lprodCnt);
		model.addAttribute("pageSize", pageVO.getPageSize());
		model.addAttribute("page", pageVO.getPage());
		model.addAttribute("lastPage", lastPage);
		
		return "/lprod/lprodPagingList";
	}
	
	@RequestMapping(path="/prodList",method=RequestMethod.GET)
	public String lprodProdList(@RequestParam("lprod_gu")String lprod_gu,Model model){
		
		List<ProdVO> prodList = lprodService.selectProd(lprod_gu);
		
		model.addAttribute("prodList", prodList);
		
		return "/lprod/prodList";
	}
	
	
}
