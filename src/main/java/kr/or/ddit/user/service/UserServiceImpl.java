package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
//	기존의 방법
//	private IUserDao userDao;
//	public UserServiceImpl(){
//		userDao = new UserDaoImpl();
//	}
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVO> getAllUser() {
		
		List<UserVO> userlist = userDao.getAllUser();
		return userlist;
	}

	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	@Override
	public UserVO selectUser(String userId) {
		
		UserVO uvo = userDao.selectUser(userId);
		return uvo;
	}

	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 조회
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVO pageVO) {
		//결과값을 두개 담아서 return 하기위해 Map 객체 사용
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.selectUserPagingList(pageVO));
		resultMap.put("userCnt", userDao.getUserCnt());
		return resultMap;
	}

	@Override
	public int insertUser(UserVO vo) {
		
		int cnt = userDao.insertUser(vo);
		
		return cnt;
	}

	@Override
	public int updateUser(UserVO vo) {
		
		int cnt = userDao.updateUser(vo);
		
		return cnt;
	}

	@Override
	public int encryptPass() {
		List<UserVO> uvolist = userDao.getAllUser();
		int cnt=0;
		for(UserVO uvo : uvolist){
			String pass = uvo.getPass();
			String encryptPass = KISA_SHA256.encrypt(pass);
			
			uvo.setPass(encryptPass);
			cnt = userDao.encryptPass(uvo);
		}
		
		return cnt;
	}
}