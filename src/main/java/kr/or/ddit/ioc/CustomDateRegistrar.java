package kr.or.ddit.ioc;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomDateRegistrar implements PropertyEditorRegistrar{

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
//		registry.registerCustomEditor(requiredType, propertyEditor);
		
	}

}
