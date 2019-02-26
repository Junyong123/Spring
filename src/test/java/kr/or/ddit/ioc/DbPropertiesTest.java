package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {
	
	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);
	
	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	@Test
	public void testDbProperties() {
		/***Given***/
		
		/***When***/
		String url = dbProperties.getUrl();
		/***Then***/
		logger.debug("dbProperties.getUrl() : {}",url);
//		logger.debug("dbProperties.getDriverClassName() : {}",dbProperties.getDriverClassName());
//		logger.debug("dbProperties.getUsername() : {}",dbProperties.getUsername());
//		logger.debug("dbProperties.getPassword() : {}",dbProperties.getPassword());
		
	}

}
