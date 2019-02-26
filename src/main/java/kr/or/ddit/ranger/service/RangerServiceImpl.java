package kr.or.ddit.ranger.service;

import java.util.List;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;

public class RangerServiceImpl implements IRangerService{

	private IRangerDao rangerDao;
	
	public RangerServiceImpl() {
		
	}
	
	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerDao = rangerDao;
	}

	@Override
	public List<String> getRangers() {
		return rangerDao.getRangers();
	}

	public void setRangerDao(IRangerDao rangerDao) {
		// new 연산자를 사용하지 않음
		this.rangerDao = rangerDao;
	}
	
	
}
