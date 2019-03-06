package kr.or.ddit.util.contorller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler({ArithmeticException.class})
	public String handelException(){
		logger.debug("arithmeticExceoption");
		return "/error/error";
	}
}
