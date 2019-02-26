package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {SpringJavaConfig.class}) // {} 배열형식 설정파일이 2가지 이상일경우 이용
public class SpringJavaConfigTest {
	
	private Logger logger = LoggerFactory.getLogger(SpringJavaConfigTest.class);
	
	// 치트키 AutoWired 동일한 타입이 없을 경우 가능 
	//@Autowired
	@Resource(name="getRangerDao") // 클래스명을 입력
	private IRangerDao rangerDao;
	
	@Resource(name="getRangerService")
	private IRangerService rangerService;
	
	@Test
	public void testRangerDao() {
		/***Given***/
		
		/***When***/
		logger.debug("rangers : {}",rangerDao.getRangers());
		/***Then***/	
		assertNotNull(rangerDao);

	}
	
	@Test
	public void testRangerService() {
		/***Given***/
		
		/***When***/
		logger.debug("RangerService : {}",rangerService.getRangers());
		/***Then***/	
		assertNotNull(rangerService);
		
	}

}
