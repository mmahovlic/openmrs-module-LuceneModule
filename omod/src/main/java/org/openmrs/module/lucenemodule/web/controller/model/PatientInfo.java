package org.openmrs.module.lucenemodule.web.controller.model;

public class PatientInfo {


	private String name;
	private String surname;
	private String middleName;
	private String description;
	private String additionalNotes;

	public PatientInfo() {
	}

	public PatientInfo(String name, String surname, String middleName,
			String description, String additionalNotes) {
		super();
		this.name = name;
		this.surname = surname;
		this.middleName = middleName;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	@Override
	public String toString() {
		return "PatientInfo [name=" + name + ", surname=" + surname
				+ ", middleName=" + middleName + ", description=" + description
				+ ", additionalNotes=" + additionalNotes + "]";
	}
}
