package org.openmrs.module.lucenemodule.web.controller.mappers;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.module.lucenemodule.web.controller.model.PatientInfo;

public class PatientInfoMapper {
	
	public static PatientInfo mapPatientFromRequest(HttpServletRequest httpServletRequest){
		
		String name = httpServletRequest.getParameter("name");
		String surname = httpServletRequest.getParameter("surname");
		String middleName = httpServletRequest.getParameter("middlename");
		String description = httpServletRequest.getParameter("description");
		String additionalNotes = httpServletRequest.getParameter("additionalnotes");
		PatientInfo patientInfo = new PatientInfo(name, surname, middleName, description, additionalNotes);
		
		return patientInfo;
		
	}

}
