package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.model.RangerVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-fconversion.xml")
public class StringDateFConverterTest {
	
	@Resource(name="rangerVO")
	private RangerVO rangerVO;
	
	@Test	
	public void testRangerVO() {
		/***Given***/
		
		/***When***/
		String userId = rangerVO.getUserId();
		Date birth = rangerVO.getBirth();
		Date regDt = rangerVO.getRegDt();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String birthStr = sdf.format(birth);
		String regDtStr = sdf2.format(regDt);
		
		
		/***Then***/
		assertNotNull(rangerVO);
		assertEquals("brown", userId);
		assertEquals("08-08-2018", birthStr);
		assertEquals("2019-02-27", regDtStr);
	}
}
