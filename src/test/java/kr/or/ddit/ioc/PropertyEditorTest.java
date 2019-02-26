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
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-type.xml")
public class PropertyEditorTest {

	@Resource(name="ragerVO")
	private RangerVO rangerVO;
	
	@Test	
	public void testRangerVO() {
		/***Given***/
		
		
		/***When***/
		String userId = rangerVO.getUserId();
		Date birth = rangerVO.getBirth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthStr = sdf.format(birth);
		
		
		/***Then***/
		assertNotNull(rangerVO);
		assertEquals("brown", userId);
		assertEquals("2018-08-08", birthStr);
		
	}

}
