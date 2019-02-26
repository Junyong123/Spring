package kr.or.ddit.ranger.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ranger.dao.IRangerDao;

@Service("rangerS")
public class RangerServiceImpl implements IRangerService{

	@Resource(name="rangerD")
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

	@Override
	public IRangerDao getRangerDao() {
		return this.rangerDao;
	}
	
}
