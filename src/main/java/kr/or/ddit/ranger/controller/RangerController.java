package kr.or.ddit.ranger.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ranger.model.RangerVO;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	/**
	* Method : getRangers
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : 전체 레인저스 리스트를 조회
	*/
	// localhost/ranger/getRangers 라고 요청시 밑의 메서드에서 요청을 처리
	// 기존의 서블릿에서는 url을 구분하는게 불가능 했었음
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		List<String> rangers = rangerService.getRangers();
		
		// 기존에는 속성을 이용.
		// request.setAttribute("rangers",ranger);
		model.addAttribute("rangers", rangers);
		 
		return "/ranger/rangerList"; // == /WEB-INF/views/ranger/rangerList.jsp
	}
	
	// localhost/ranger/getRanger?listIndex=2 라고 요청시 밑의 메서드에서 요청을 처리
	// vo객체에 동일한 이름의 파라미터가 존재하면 파라미터를 해당 필드에 바인딩 시켜준다
	@RequestMapping("/getRanger")
	public String getRanger(RangerVO rangerVO,Model model) {
		// RangerVO rangerVO 에있는 파리미터 listindex를 알아서 핸들러 어뎁터가 매핑해준다
		String ranger =rangerService.getRanger(rangerVO.getListIndex());
		
		model.addAttribute("ranger",ranger);
		
		return "/ranger/ranger";
	}
	
//	@RequestMapping("/getRanger")
//	public String getRanger(HttpServletRequest req,Model model) {
//		int listIndex = Integer.parseInt(req.getParameter("listIndex"));
//		String ranger =rangerService.getRanger(listIndex);
//		
//		model.addAttribute("ranger",ranger);
//		
//		return "/ranger/ranger";
//	}
}
