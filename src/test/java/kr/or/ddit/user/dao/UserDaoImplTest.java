package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UserDaoImplTest extends LogicTestConfig{
	
	@Resource
	private IUserDao userDao;
	
	@Before
	public void setup(){
		
		userDao.deleteUser("test1");
	}
	
	@After
	public void tearDown(){
	}

	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVO> list = userDao.getAllUser();
		
		/***Then***/
		assertNotNull(list);
		assertEquals(105, list.size());
		
	}
	
	@Test
	public void testSelectUser(){
		/***Given***/
		
		/***When***/
		UserVO userVO = userDao.selectUser("brown");
		
		/***Then***/
		assertNotNull(userVO);

	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		List<UserVO> userList = userDao.selectUserPagingList(pageVO);
		for(UserVO vo : userList){
			System.out.println(vo.toString());
		}

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());

	}
	
	@Test
	public void testGetUserCnt(){
		/***Given***/
		
		/***When***/
		int userCnt = userDao.getUserCnt();
		
		/***Then***/
		assertEquals(105, userCnt);
		
	}
	
	@Test
	public void testPagination(){
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
	
		/***When***/
		System.out.println(Math.ceil((userCnt*1.0/pageSize)));
		int lastPage = (int)Math.ceil((userCnt*1.0/pageSize));
		System.out.println(lastPage);
		
		/***Then***/
		assertEquals(11, lastPage);
		
	}
	
	@Test
	public void testInsetUser(){
		/***Given***/
		UserVO uvo = new UserVO();
		int cnt = 0;
		Date date = new java.util.Date();
		
		uvo.setUserId("test1");
		uvo.setUserNm("테스트");
		uvo.setAlias("별명");
		uvo.setAddr1("대전 중구 대흥로 76");
		uvo.setAddr2("2층 ddit");
		uvo.setZipcode("34942");
		uvo.setPass("testpass");
		uvo.setReg_dt(date);
		
		userDao.deleteUser("test1");
		/***When***/
		cnt = userDao.insertUser(uvo);

		/***Then***/
		assertEquals(1, cnt);
	}
}