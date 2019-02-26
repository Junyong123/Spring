package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

// 설정정보
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {
	
	private Logger logger = LoggerFactory.getLogger(AnnotationScanTest.class);
	
	// rangerDao 주입
	@Resource(name="rangerDaoImpl")
	private IRangerDao rangerDao;
	
	// rangerService 주입
	@Resource(name="rangerServiceImpl")
	private IRangerService rangerService;
	
	// 두개의 스프링 빈이 정상적으로 생성 되었는지 테스트
	
	/**
	* Method : testAnnotation
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : rangerDao 빈 컴퍼넌트 스캔 테스트
	*/
	@Test
	public void testAnnotation() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerDao = {}",rangerDao.getRangers());
		logger.debug("rangerService = {}",rangerService.getRangers());
		/***Then***/
		
		assertNotNull(rangerDao);
		assertNotNull(rangerService);

	}
	
	/**
	* Method : testRangerDaoEqualse
	* 작성자 : pc15
	* 변경이력 :
	* Method 설명 : rangerService 스프링 빈에 주입된 rangerDao 객체가 rangerDAo 스프링 빈과 일치하는지 테스트
	*/
	@Test
	public void testRangerDaoEqualse() {
		/***Given***/
		
		
		/***When***/
		IRangerDao rangerServiceDao = rangerService.getRangerDao();
		
		/***Then***/
		assertEquals(rangerDao, rangerServiceDao);
		
	}

}
