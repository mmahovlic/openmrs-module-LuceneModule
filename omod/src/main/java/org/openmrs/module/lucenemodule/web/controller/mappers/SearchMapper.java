package org.openmrs.module.lucenemodule.web.controller.mappers;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.module.lucenemodule.web.controller.model.SearchRequest;

public class SearchMapper {
	
	public static SearchRequest mapFromSearchRequest(HttpServletRequest request){
		
		String id = request.getParameter("id");
		String query = request.getParameter("query");
		SearchRequest searchRequest = new SearchRequest(id, query);
		
		return searchRequest;
		
	}

}
