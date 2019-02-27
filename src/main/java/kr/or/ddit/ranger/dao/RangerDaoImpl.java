package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("rangerDao")
public class RangerDaoImpl implements IRangerDao{
	
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
	
	
}
