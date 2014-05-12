package org.openmrs.module.lucenemodule.web.controller.model;

import org.apache.http.HttpResponse;

public class IndexingResult {
	
	private HttpResponse httpResponse;
	private String error="";
	
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}
	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "IndexingResult [httpResponse=" + httpResponse + ", error="
				+ error + "]";
	}
}
