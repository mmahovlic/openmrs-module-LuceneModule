package org.openmrs.module.lucenemodule.web.controller.model;

import org.apache.solr.client.solrj.beans.Field;

public class PatientInfo {
	
	@Field
	private String id;
	@Field
	private String name;
	@Field
	private String surname;
	@Field
	private String middleName;
	@Field
	private String illnessDescription;
	@Field
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
