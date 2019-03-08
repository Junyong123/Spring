package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice {
	
	private Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	public void beforeMethod(JoinPoint joinPoint){
		// Joinpoint : 우리가 원래 만든 로직 ,우리가 만든 메소드의 정보를 받아 올 수 있다
		logger.debug("********************");
		logger.debug("loggingAdvice before");
		logger.debug("loggingAdvice before");
		logger.debug("loggingAdvice before");
		logger.debug("********************");
	}
	
	public void afterMethod(JoinPoint joinPoint){
		// 클래스 이름
		String className = joinPoint.getTarget().getClass().getName();
		// 메소드 이름
		String methodName = joinPoint.getSignature().getName();
		
		logger.debug("********************");
		logger.debug("loggingAdvice after");
		logger.debug("className : {}",className);
		logger.debug("methodName : {}",methodName);
		logger.debug("********************");
	}
	
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		// 핵심 로직 호출 전
		long startTime = System.currentTimeMillis();
		
		// 핵심 로직 호출
		Object[] args = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(args);
		
		// 핵심 로직 호출 후
		long endTime = System.currentTimeMillis();
		logger.debug("profileingTime : {}",endTime-startTime);
		
		return returnObj;
	}
	
}
