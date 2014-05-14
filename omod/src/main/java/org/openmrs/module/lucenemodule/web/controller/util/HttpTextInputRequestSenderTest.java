package org.openmrs.module.lucenemodule.web.controller.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openmrs.module.lucenemodule.web.controller.model.PatientInfo;

public class HttpTextInputRequestSenderTest {

	private PatientInfo patientInfo;
	
	@Test
	public void test() {
		String id = "Mario";
		String name ="Mahovlić";
		String surname ="Test";
		String middleName = "Test1";
		String description = "Test2";
		String additionalNotes ="Test3";
		patientInfo = new PatientInfo(id, name, surname, middleName, description, additionalNotes);
		HttpTextInputRequestSender.sendHttpPostRequest(patientInfo);
	}

}
