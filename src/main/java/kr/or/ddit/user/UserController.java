package kr.or.ddit.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/user")
@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	* Method : userAllList
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		List<UserVO> ulist = userService.getAllUser();
		
		model.addAttribute("ulist",ulist);
		
		return "/user/userAllList";
	}
	
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVO pageVO,Model model){
		
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		int userCnt = (Integer)resultMap.get("userCnt");
		int lastPage = (Integer)(userCnt/pageVO.getPageSize() + (userCnt%pageVO.getPageSize() > 0 ? 1 : 0));
		model.addAttribute("userCnt",userCnt);
		model.addAttribute("lastPage",lastPage);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVO.getPageSize());
		model.addAttribute("page",pageVO.getPage());
		
		return "/user/userPagingList";
	}
}
