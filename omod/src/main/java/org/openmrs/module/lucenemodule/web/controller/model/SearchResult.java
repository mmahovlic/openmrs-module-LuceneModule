package org.openmrs.module.lucenemodule.web.controller.model;

import org.apache.http.HttpResponse;

public class SearchResult {
	
	private String searchResultJson;
	private String error="";
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSearchResultJson() {
		return searchResultJson;
	}
	public void setSearchResultJson(String searchResultJson) {
		this.searchResultJson = searchResultJson;
	}
	@Override
	public String toString() {
		return "SearchResult [searchResultJson=" + searchResultJson
				+ ", error=" + error + "]";
	}



}
