package kr.or.ddit.ranger.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ranger.dao.IRangerDao;

@Service("rangerService")
public class RangerServiceImpl implements IRangerService{

	@Resource(name="rangerDao")
	private IRangerDao rangerDa;
	
	public RangerServiceImpl() {
		
	}
	
	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerDa = rangerDao;
	}

	@Override
	public List<String> getRangers() {
		return rangerDa.getRangers();
	}

	public void setRangerDao(IRangerDao rangerDao) {
		// new 연산자를 사용하지 않음
		this.rangerDa = rangerDao;
	}

	@Override
	public IRangerDao getRangerDao() {
		return this.rangerDa;
	}

	@Override
	public String getRanger(int index) {
		
		return rangerDa.getRanger(index);
		// 알트 쉬프트 알 - 이름바꾸기 기능
	}
	
	
	
}
