package org.openmrs.module.lucenemodule.web.controller.model;

public class SearchRequest {

	private String id;
	private String query;
	
	public SearchRequest() {	
	}
	
	public SearchRequest(String id, String query) {
		super();
		this.id = id;
		this.query = query;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	@Override
	public String toString() {
		return "SearchRequest [id=" + id + ", query=" + query + "]";
	}
	
}
