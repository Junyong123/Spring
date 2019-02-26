package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("rangerD")
public class RangerDaoImpl implements IRangerDao{
	
	public RangerDaoImpl() {
		
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
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("moon");
		rangers.add("sally");
		
		return rangers;
	}
	
	
}
