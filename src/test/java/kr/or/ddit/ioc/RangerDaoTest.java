package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;

@RunWith(SpringJUnit4ClassRunner.class)
// servlet-context.xml, application-context.xml
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml",
						"classpath:kr/or/ddit/config/spring/servlet-context.xml"})
public class RangerDaoTest {

	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;
		/***When***/
		String ranger = rangerDao.getRanger(index);
		/***Then***/
		assertEquals("brown",ranger);
	}
	
	@Test
	public void testGetRanger_overflowIndex() {
		/***Given***/
		int index = 10;
		/***When***/
		String ranger = rangerDao.getRanger(index);
		/***Then***/
		assertEquals("sally",ranger);
	}

}
