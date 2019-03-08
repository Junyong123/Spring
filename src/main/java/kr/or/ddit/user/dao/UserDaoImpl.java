package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/**
	 * Method : getAllUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	@Override
	public List<UserVO> getAllUser(){
		List<UserVO> userList = sqlSessionTemplate.selectList("user.getAllUser");
		
		return userList;
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
	public UserVO selectUser(String userId){
		UserVO userVO = sqlSessionTemplate.selectOne("user.selectUser", userId);
		
		return userVO;
	}

	/**
	 * Method : selectUserPagingList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param pageVO
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVO> selectUserPagingList(PageVO pageVO) {
		List<UserVO> userList = sqlSessionTemplate.selectList("user.selectUserPagingList", pageVO);
		
		return userList;
	}

	/**
	 * Method : getUserCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수를 조회
	 */
	@Override
	public int getUserCnt() {
		int userCnt = sqlSessionTemplate.selectOne("user.getUserCnt");
		
		return userCnt;
	}

	@Override
	public int insertUser(UserVO vo) {
		int cnt = sqlSessionTemplate.insert("user.insertUser",vo);
		
		return cnt;
	}

	/**
	* Method : deleteUser
	* 작성자 : pc15
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		int cnt = sqlSessionTemplate.delete("user.deleteUser",userId);
		
		return cnt;
	}

	@Override
	public int updateUser(UserVO vo) {
		int cnt = sqlSessionTemplate.update("user.updateUser",vo);
		return cnt;
	}

	@Override
	public int encryptPass(UserVO vo) {
		int cnt = sqlSessionTemplate.update("user.encryptPass",vo);
		return cnt;
	}
}
