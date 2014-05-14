package org.openmrs.module.lucenemodule.web.controller.model;

public class PatientInfo {

	private String id;
	private String name;
	private String surname;
	private String middleName;
	private String illnessDescription;
	private String additionalNotes;

	public PatientInfo() {
	}

	public PatientInfo(String id, String name, String surname,
			String middleName, String illnessDescription, String additionalNotes) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.middleName = middleName;
		this.illnessDescription = illnessDescription;
		this.additionalNotes = additionalNotes;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIllnessDescription() {
		return illnessDescription;
	}

	public void setIllnessDescription(String illnessDescription) {
		this.illnessDescription = illnessDescription;
	}

	@Override
	public String toString() {
		return "PatientInfo [id=" + id + ", name=" + name + ", surname="
				+ surname + ", middleName=" + middleName
				+ ", illnessDescription=" + illnessDescription
				+ ", additionalNotes=" + additionalNotes + "]";
	}
	
	
}
