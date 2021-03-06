package kr.or.ddit.ranger.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVO;
import kr.or.ddit.ranger.service.IRangerService;

@SessionAttributes("boardGb") // @ModelAttribute에 동일한 속성이 있으면 session에 넣어줌
							  // ${sessionScope.boardGb }로 불러오기도 가능
@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	private Logger logger = LoggerFactory.getLogger(RangerController.class);
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	// @ModelAttrbute가 적용된 메소드는 
	// @RequestMapping이 적용된 메소드가 실행되기 전에
	// 먼저 실행되고 리턴하는 객체를 model객체에 속성으로 넣어준다
	
	@ModelAttribute("boardGb")
	public List<String> boardGb(){
		logger.debug("boardGb");
		
		List<String> boardGbList = new ArrayList<String>();
		boardGbList.add("공지사항");
		boardGbList.add("Q&A");
		boardGbList.add("경조사");
		boardGbList.add("영업게시판");
		
		return boardGbList;
	}
	
	@ModelAttribute("boardGb2")
	public List<String> boardGb2(){
		logger.debug("boardGb");
		
		List<String> boardGbList = new ArrayList<String>();
		boardGbList.add("공지사항2");
		boardGbList.add("Q&A2");
		boardGbList.add("경조사2");
		boardGbList.add("영업게시판2");
		
		return boardGbList;
	}
	
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
	// 또한 커맨드 객체(vo객체)는 model에 속성으로 자동으로 추가 된다.
	@RequestMapping("/getRanger")
	public String getRanger(@ModelAttribute("rVo")RangerVO rangerVO,Model model) {
		// RangerVO rangerVO 에있는 파리미터 listindex를 알아서 핸들러 어뎁터가 매핑해준다
		String ranger =rangerService.getRanger(rangerVO.getListIndex());
		
		model.addAttribute("ranger",ranger);
		// == model.addAttribute("rangerVO",rangerVO);
		
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
	
	// @PathVariable : url의 일부 값을 변수로 치환해서
	// 개발자가 해당 값을 사용하기 쉽게 받을수 있는 @
	// localhost/ranger/pathVariable/???
	@RequestMapping(path="/pathVariable/{userId}")
	public String pathVariable(@PathVariable("userId")String userId){
		
		logger.debug("userId : {}",userId);
		
		return "/ranger/ranger";
	}
	
	// 클라이언트가 요청시 보내는 header정보를 쉽게
	// 사용할 수 있도록 값을 받을 수 있다
	// localhost/ranger/requestHeader
	@RequestMapping(path="/requestHeader")
	public String requestHeader(@RequestHeader("Accept")String acceptHeader){
		
		logger.debug("acceptHeader : {}",acceptHeader);
		
		return "/ranger/ranger";
	}
	
	/**
	* Method : getRangersMav
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : 레인전스 전체 정보를 ModelAndView 객체를 통해 리턴
	*/
	@RequestMapping(path="/getRangersMav")
	public ModelAndView getRangersMav(){
		List<String> rangers = rangerService.getRangers();
		ModelAndView mav = new ModelAndView();
		mav.addObject("rangers", rangers);
		
		// viewName
		mav.setViewName("ranger/rangerList");
		
		
		return mav;
	}
	
	@RequestMapping(path="/getRagerParam")
	public String getRangerParam(@RequestParam(name="listIndex",defaultValue="0")int listIndex,Model model){
		
		// 위에 public String getRanger(@ModelAttribute("rVo")RangerVO rangerVO,Model model) 이걸 쉽게 하는 방법
		
		String ranger = rangerService.getRanger(listIndex);
		model.addAttribute("ranger",ranger);
		
		return "ranger/ranger"; 
	}
	
}
