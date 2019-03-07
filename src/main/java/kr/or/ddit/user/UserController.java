package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
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
//		return "userAllListTiles";
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
		
		return "userPagingTiles";
	}
	
	/**
	 * Method : userPagingListAjaxView
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 뷰
	 */
	@RequestMapping("/userPagingListAjaxView")
	public String userPagingListAjaxView(){
		return "userPagingListAjaxTiles"; //tiles 설정파일의 definition 이름(name)과 동일함
	}
	
	/**
	 * Method : userPagingListAjax
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @param model
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 ajax 요청 처리
	 */
	@RequestMapping("/userPagingListAjax")
	public String userPagingListAjax(PageVO pageVO, Model model){
		int page = pageVO.getPage();
		int pageSize = pageVO.getPageSize();
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		
		int userCnt = (int) resultMap.get("userCnt");
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0);
		int lastPageStartPage = ((lastPage - 1) / 10) * 10 + 1;
		int startPage = ((page - 1) / 10) * 10 + 1; 
		int endPage = startPage + 10 - 1;
		
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("lastPageStartPage", lastPageStartPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "jsonView"; //bean 객체의 이름과 동일함
	}
	
	@RequestMapping("/userPagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVO pageVO, Model model){
		int page = pageVO.getPage();
		int pageSize = pageVO.getPageSize();
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		
		int userCnt = (int) resultMap.get("userCnt");
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0);
		int lastPageStartPage = ((lastPage - 1) / 10) * 10 + 1;
		int startPage = ((page - 1) / 10) * 10 + 1; 
		int endPage = startPage + 10 - 1;
		
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("lastPageStartPage", lastPageStartPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		logger.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		logger.debug("userList : {}", userList);
		logger.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		
		return "/user/userPagingListAjaxHtml";
	}
	
	@RequestMapping(path="/user",method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId,Model model){
		
		UserVO userVO = userService.selectUser(userId);
		model.addAttribute("userVO",userVO);
		
//		return "/user/user";
		return "userTiles";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam("userId")String userId,Model model) throws IOException{
		
		resp.setContentType("application/octet-stream"); // octet-stream : 8비트로 타입을 지정한후 인코딩
		
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
	
	/**
	* Method : userForm
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록폼 요청
	*/
	@RequestMapping(path="/userForm",method=RequestMethod.GET)
	public String userForm(){ 
		
		return "/user/userForm";
	}
	
	@RequestMapping(path="/userForm",method=RequestMethod.POST)
	public String userForm(@RequestPart("profile")MultipartFile profile,UserVO userVO
			,HttpSession session,Model model) throws IllegalStateException, IOException{
		
		UserVO duplicateUserVO = userService.selectUser(userVO.getUserId());
		
		// 중복체크 통과(신규등록)
		if(duplicateUserVO == null){
			// 사용자 프로파일을 업로드 한경우
			String filename = "";
			String reqlFilename = "";
			if(profile.getSize()>0){
				filename = profile.getOriginalFilename();
				reqlFilename = "d://picture"+UUID.randomUUID().toString();
				
				profile.transferTo(new File(reqlFilename)); // 경로로 파일을 다운로드한다.
				
			}
			userVO.setFilename(filename);
			userVO.setRealFilename(reqlFilename);
			
			// 사용자 비밀번호 암호화 로직
			// 비밀번호 수정 요청여부
			// 사용자가 값을 입력하지 않은 경우 -> 기존비밀번호 유지
			if(userVO.getPass().equals("")){
				UserVO userVOForPass = userService.selectUser(userVO.getUserId());
				userVO.setPass(userVOForPass.getPass());
			}	
			// 사용자가 비밀번호를 신규 등록한 경우 -> 새로운 비밀번호로 교체
			else{
				userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			}
			
			int cnt=0;
			try{
				cnt = userService.insertUser(userVO);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(cnt==1){
				session.setAttribute("msg", "정상 등록 되었습니다");  
				return "redirect:/user/userPagingList"; // context작업 필요
			}
			else{
				return "/user/userForm";
			}
		}
		// 중복체크 실패
		else{
			model.addAttribute("msg","중복체크에 실패 했습니다.");
			return "/user/userForm";
		}
		
	}
	
	@RequestMapping(path ="/userModifyForm",method=RequestMethod.POST)
	public String userModify(
							UserVO userVO,
							@RequestPart("profile")MultipartFile profilePart, // profile은 jsp에서 사진파일
							Model model,
							RedirectAttributes ra
			) throws IOException, ServletException{
		
	    String filename = "";
	    String realFilename = "";
	      
	    if(profilePart.getSize()>0){
		 	filename = profilePart.getOriginalFilename(); 
		 	realFilename = "d:\\picture\\" +UUID.randomUUID().toString();
		 	
		 	profilePart.transferTo(new File(realFilename)); // 해당 경로로 파일전송
	    }
		
	    userVO.setFilename(filename);
	    userVO.setRealFilename(realFilename);
      
	    int result = userService.updateUser(userVO);
	     
	    if(result == 1){
	    	// redirect는 db에서 값을 수정했을 경우 주소창을 바꿔서 그 전page로 돌아가지 않게 해주는 기능
	    	// 새로고침 했을 경우 수정되기 전에 값이 나옴 redirect를 사용하지 않았을 경우
	    	
	    	//redirect 파라미터 보내는 방법
	    	// 1.url로 작성
	    	// "redirect:/user/user?userId="+userVO.getUserId();
	    	return "redirect:/user/user?userId="+userVO.getUserId(); 
	    	
	    	// 2.model 객체의 속성 : 저장된 속성을 자동으로 파라미터 변환
	    	// model.addAttribute("userId",userO.getUserId();
	    	// return "redirect:/user/user";
	    	
	    	// 3.RedirectAttributes(ra) 객체를 이용
	    	// ra.addAttribute("userId",userVO.getUserId());
	    	// ra.addFlashAttribute("msg","정상 등록 되었습니다."); // 잠깐 저장되는 기능 (한번만읽고 바로 삭제)
	    	// return "redirect:/user/user";
	    	
	    	
	    }else{
	    	return "/user/userModifyForm";
	    }
		
	}
	
	@RequestMapping(path ="/userModifyForm",method=RequestMethod.GET)
	public String userModify(@RequestParam("userId")String userId,Model model,HttpServletRequest req){
		
		UserVO userVO = userService.selectUser(userId);
      
      	model.addAttribute("userVO",userVO);
      	
		return "/user/userModifyForm";
	}
}
