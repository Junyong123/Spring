package kr.or.ddit.ioc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class CustomDateRegistrar implements PropertyEditorRegistrar{

	/**
	* Method : registerCustomEditors
	* 작성자 : pc15
	* 변경이력 :
	* @param registry
	* Method 설명 : String 형태를 원하는 형태의 날짜로 수정함
	*/
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Date.class , new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
	}
}
