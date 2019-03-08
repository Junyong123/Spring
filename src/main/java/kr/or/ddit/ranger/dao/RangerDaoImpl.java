package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("rangerDao")
public class RangerDaoImpl implements IRangerDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	private List<String> rangers;

	public RangerDaoImpl() {
		rangers = new ArrayList<String>();
		
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("moon");
		rangers.add("sally");
		
	}

	/**
	* Method : getRangers
	* 작성자 : pc15
	* 변경이력 :
	* @return
	* Method 설명 : 전체 레인저스 조회(임의의 값)
	*/
	@Override
	public List<String> getRangers() {
		return rangers;
	}

	@Override
	public String getRanger(int listIndex) {
		if(listIndex<0) {
			return rangers.get(0);
		}else if(listIndex>rangers.size()-1) {
			return rangers.get(rangers.size()-1);
		}else 
			return rangers.get(listIndex);
	}

	@Override
	public List<Map<String, String>> getRangersDb() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ranger.getRangersDb");
	}

	@Override
	public Map<String, String> getRanger(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ranger.getRanger",id);
	}

	@Override
	public int insertRanger(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("ranger.insertRanger",map);
	}

	@Override
	public int deleteRanger(String id) {
		// TODO Auto-generated method stub
		return sqlSession.delete("ranger.deleteRanger",id);
	}

	@Override
	public int deleteRangerDept(String id) {
		// TODO Auto-generated method stub
		return sqlSession.delete("ranger.deleteRangerDept",id);
	}
	
	
	
}
