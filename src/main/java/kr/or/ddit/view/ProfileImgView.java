package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

public class ProfileImgView implements View{

	@Resource(name="userService")
	private IUserService userService;
	
	@Override
	public String getContentType() {
		return "image"; // 타입이 무엇인지 작성
	}

	// 개발자는 이미지 경로를 model객체에 "path"라는 속성으로 설정한다
	// 개발자는 사용자 아이디를 model객체에 userId라는 속성으로 설정한다
	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
//		resp.setHeader("Content-Disposition", "attachment;filename=profile.png");
//		resp.setContentType("application/octet-stream");
		resp.setContentType("image/png");
		// 1.사용자 아이디 파라피터 확인
		String userId = (String) model.get("userId");
		
		// 2.해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVO userVO = userService.selectUser(userId);
		
		// 3-1.realFilename이 존재할 경우
		// 3-1-1.해당 경로의 파일을 FileInputStream으로 읽는다.
		FileInputStream fis;
		if(userVO != null&&userVO.getRealFilename() != null){
			fis = new FileInputStream(new File(userVO.getRealFilename()));
		}
		
		// 3-2 realFilename이 존재하지 않을 경우
		// 3-2-1./upload/noimg.png (application.getRealPath())
		else{
			ServletContext application = req.getServletContext(); // application 객체 생성
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		// 4.FileInputStream을 response객체의 outputStream객체를 write
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
