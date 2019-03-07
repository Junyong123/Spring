package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.ibatis.javassist.compiler.NoFieldException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.exception.NoFileException;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.validator.UserVoValidator;

@Controller
public class MvcController {
	
	private Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	/**
	* Method : view
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : part를 테스트할 view 요청
	*/
	@RequestMapping("/mvc/view")
	public String view(){
		
		return "/mvc/view";
	}
	
	/**
	* Method : fileupload
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : fileupload 처리 요청 테스트
	*/
	// 파라미터 : userId(text), profile(file)
	@RequestMapping("/mvc/fileupload")
	public String fileupload(@RequestParam("userId") String userId,
				@RequestPart("profile") MultipartFile multipartFile
			){
		// @RequestParam 파라미터를 가져올 때 사용. request.getParameter과 동일함

		logger.debug("userId : {}",userId);
		logger.debug("multipartFile.getOriginalFilename() : {}", multipartFile.getOriginalFilename());
		logger.debug("multipartFile.getName() : {}", multipartFile.getName());
		logger.debug("multipartFile.getSize() : {}", multipartFile.getSize());
		
		// 파일 업로드
		String fileName = multipartFile.getOriginalFilename()+"_"+UUID.randomUUID().toString();
		File profile = new File("d:\\picture\\"+fileName);
		
		try {
			multipartFile.transferTo(profile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/mvc/view";
	}
	
	@RequestMapping("/textView")
	public String textView(){
		
		return "/mvc/textView";
	}
	
	// @RequestParam 어노테이션을 적용하지 않아도
	// 인스턴스명이랑 동일하면 자동적으로 바인딩
	// 파라미터명이랑 인스턴스 명이랑 다른경우 --> @RequesParam
	// BindingResult객체는 command객체 (vo)에 바인딩 과정에서 발생한 결과를 담는 객체로 반드시
	// coomand객체 메소드 인자 뒤에 위치해야 한다
	// O : UserVO userVO, BindingResult result, Model model
	// x : UserVO userVO, Model model ,BindingResult result
	@RequestMapping("/textReq")
	public String textReq(UserVO userVO,BindingResult result,Model model){
		new UserVoValidator().validate(userVO,result); // validator 실행
		
		logger.debug("userId : {}",userVO.getUserId());
		logger.debug("pass : {}",userVO.getPass());
		
		if(result.hasErrors()){ // 에러가 있다면
			logger.debug("hasError");
			return "/mvc/textView";
		}
		
		// pass : 8자리 이상
		if(userVO.getPass().length()<8){
			model.addAttribute("passwordLengthMsg","비밀번호는 8자리 이상이어야 합니다");
		}
		
		return "/mvc/textView";
	}
	
	@RequestMapping("/textReqJsr303")
	public String textReqJsr303(@Valid UserVO userVO,BindingResult result){ // vo에 @Valid 선언
		logger.debug("result.hasErrors(jsr303) : {}",result.hasErrors());
		
		return "/mvc/textView";
	}
	
	@RequestMapping("/textReqValJsr303")
	public String textReqValJsr303(@Valid UserVO userVO,BindingResult result){
		logger.debug("result.hasErrors(Valjsr303) : {}",result.hasErrors());
		
		return "/mvc/textView";
	}
	
	@InitBinder // 'textReqValJsr303'와 한 묶음
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UserVoValidator());
	}
	
	/**
	* Method : ArithmeticException
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : arithmethic 예외 강제 발생
	*/
	@RequestMapping("/throwArith")
	public String ArithmeticException(){
		
		if(1==1) // return에 뜨는 에러를 없애기 위해 만든 쓰레기값
			throw new ArithmeticException();
		
		return "/mvc/textView";
	}
	
	@RequestMapping("/throwNoFileException")
	public String throwNoFileException() throws NoFileException{
		
		// 여기서 부터
		Resource resource = new ClassPathResource("kr/or/ddit/config/spring/no-exsits.xml"); // 경로입력
		
		try { // 에러를 잡고 우리가 지정한 새로운 에러를 발생시켜줌
			resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoFileException();
		}
		return "/mvc/textView";
	}
	
	@RequestMapping("/jsonResponse")
	public String jsonResponse(Model model){
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("moon");
		list.add("james");
		list.add("cony");
		
		model.addAttribute("list",list);
		return "jsonView"; // servlet context에 동일한 이름에 bean이 있다면 order 순위에 맞추어 실행
	}
	
	
	@RequestMapping("/jsonResponseViewObj")
	public View jsonResponseViewObj(Model model){
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("moon");
		list.add("james");
		list.add("cony");
		list.add("differce");
		
		model.addAttribute("list",list);
		return new MappingJackson2JsonView();
	}
	
	@RequestMapping("/profileImgView")
	public String profilImgView(@RequestParam(name="userId",defaultValue="brown")String userId,Model model){
		
		model.addAttribute("userId",userId);
		return null;
	}
	
	@RequestMapping("/helloTiles")
	public String helloTiles(){
		
		// 순서
		// 1.BeanNameViewResolver
		// helloTiles()에서 리턴하는 문장열에 해당하는 bean id를 갖는 스프링 빈이 있는지 확인 
		// 있으면 -> 해당 스프링 객체를 사용 하여 응답이 전달
		// 없으면 -> 다음 view Resolver 에서 처리
		
		// 2.TilesViewResolver
		// helloTiles()에서 리턴하는 문자열이
		// tilesConfigurer에 설정한 탕리즈 설정파일의 definition 이름(name)과 동일한 선언이 있는지 확인
		// 있으면 -> 해당 tiles 설정대로 (layout extends) 응답 생성
		// 없으면 -> 다음 view Resolver에서 처리
		
		
		return "ht";
	}
}
