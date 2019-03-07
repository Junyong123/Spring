package kr.or.ddit.mvc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVO;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	
	private Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@ModelAttribute("rangerList")
	public List<String> getRangers(){
		return rangerService.getRangers();
	}
	
	// BeanNameViewResolver
	// 1.viewName : jsonView
	// 2.ModelAndView("jsonView")
	
	@RequestMapping("/jsonView")
	public String jsonView(){
		return "jsonView";
	}
	
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView(){
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping("/ajaxView")
	public String ajaxView(){
		return "/mvc/ajaxView";
	}
	
	@RequestMapping(path="/responseBody",produces={"application/json"})
	@ResponseBody
	public List<String> responseBody(){
		return rangerService.getRangers();
	}
	
	@RequestMapping(path="/requestBody",produces={"application/json"})
	@ResponseBody
	public RangerVO requestBody(@RequestBody RangerVO rangerVO){
		logger.debug("rangerVO : {}",rangerVO);
		
		rangerVO.setUserId(rangerVO.getUserId()+"_server");
		rangerVO.setBirth(new Date());
		
//		return rangerService.getRangers();
		return rangerVO;
	}
}
