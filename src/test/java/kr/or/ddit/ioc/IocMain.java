package kr.or.ddit.ioc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

public class IocMain {
	private static Logger logger = LoggerFactory.getLogger(IocMain.class);
	public static void main(String[] args) {
		
		// 스프링 컨테이너 생성
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-context.xml");
	
		// DL(Dependency Lookup) : spring container로 부터 객체를 요청
		IRangerDao rangerDao = (IRangerDao) context.getBean("rangerDaoSpringBean");
		
		// Dao로 테스트
		List<String> rangers = rangerDao.getRangers();
		
		for(String ranger : rangers) {
			logger.debug("ranger : {}", ranger);
		}
		
		logger.debug("=======================================================");
		
		// rangerService DL
		IRangerService rangerService = context.getBean("rangerService",IRangerService.class);
		
		// service로 test
		List<String> rangers2 = rangerService.getRangers();
		
		for(String ranger : rangers2) {
			logger.debug("ranger : {}", ranger);
		}
		
		logger.debug("=======================================================");
		logger.debug("rangerService생성자 주입(rangerDao)");
		
		// rangerService DL
		IRangerService rangerServiceConstructor = context.getBean("rangerServiceConstructor",IRangerService.class);
		
		// service로 test
		List<String> rangers3 = rangerService.getRangers();
		
		for(String ranger : rangers3) {
			logger.debug("ranger : {}", ranger);
		}
		
	}
	
}
