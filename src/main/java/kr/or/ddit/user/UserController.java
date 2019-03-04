package kr.or.ddit.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	/**
	* Method : userPagingList
	* 작성자 : pc15
	* 변경이력 :
	* @param pageVO
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
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
	
	@RequestMapping(path="/user",method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId,Model model){
		
		UserVO userVO = userService.selectUser(userId);
		model.addAttribute("userVO",userVO);
		
		return "/user/user";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam("userId")String userId,Model model) throws IOException{
		
		resp.setContentType("application/octet-stream");
		
		UserVO userVO = userService.selectUser(userId);
		
		FileInputStream fis;
		if(userVO != null&&userVO.getRealFilename() != null){
			fis = new FileInputStream(new File(userVO.getRealFilename()));
		}
		else{
			ServletContext application = req.getServletContext(); // application 객체 생성
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		ServletOutputStream sos  = resp.getOutputStream();
		byte[] buff = new byte[512]; // read하기 위한 byte 필요
		int len = 0;
		
		while((len  = fis.read(buff)) > -1){ // 파일이 없으면 -1이 됨
			sos.write(buff);
		}
		sos.close(); // stream은 종료를 해줘야 한다
		fis.close();
	}
	
}
