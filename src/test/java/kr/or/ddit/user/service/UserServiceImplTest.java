package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImplTest extends LogicTestConfig{
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void setup(){
	}

	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVO> list = userService.getAllUser();
		
		/***Then***/
		assertNotNull(list);
		assertEquals(105, list.size());
	}
	
	@Test
	public void testSelectUser(){
		/***Given***/
		
		/***When***/
		UserVO userVO = userService.selectUser("brown");

		/***Then***/
		assertNotNull(userVO);
	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		List<UserVO> userList = (List<UserVO>)resultMap.get("userList");
		int userCnt = (Integer)resultMap.get("userCnt");
		
		for(UserVO vo : userList){
			System.out.println(vo.toString());
		}
		
		System.out.println("userCnt : " + userCnt);

		/***Then***/
		//userList
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		//userCnt
		assertEquals(105, userCnt);
	}
	
//	@Test
	public void encryptPassTest(){
		/***Given***/
		
		/***When***/
		int cnt = userService.encryptPass();
		/***Then***/
		assertEquals(1, cnt);

	}
}