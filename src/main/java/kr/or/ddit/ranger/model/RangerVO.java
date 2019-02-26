package kr.or.ddit.ranger.model;

import java.util.Date;

public class RangerVO {
	private String userId;
	private String userNm;
	private Date birth;
	
	public RangerVO() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "RangerVO [userId=" + userId + ", userNm=" + userNm + ", birth=" + birth + "]";
	}
	
	
	
}
