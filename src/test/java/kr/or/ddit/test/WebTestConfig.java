package kr.or.ddit.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/servlet-context.xml",
				"classpath:kr/or/ddit/config/spring/application-context.xml"})
@WebAppConfiguration // 스프링 컨테이너를 만들때 WebApplicationContext로 생성
					 // 미적용시 applcationContext
public class WebTestConfig {
	
	@Autowired
	private WebApplicationContext context;
	protected MockMvc mockMvc;
	
	// @BeforeClass (Static --> 사용빈도 낮음)
	//   @Before - @Test -@After
	//   @Before - @Test -@After
	// .......
	// @AfterClass (Static --> 사용빈도 낮음)
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
}
