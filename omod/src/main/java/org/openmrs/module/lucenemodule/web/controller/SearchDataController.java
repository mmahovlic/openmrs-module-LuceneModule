package org.openmrs.module.lucenemodule.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.lucenemodule.web.controller.mappers.SearchMapper;
import org.openmrs.module.lucenemodule.web.controller.model.SearchRequest;
import org.openmrs.module.lucenemodule.web.controller.model.SearchResult;
import org.openmrs.module.lucenemodule.web.controller.util.HttpRequestSenderUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "module/lucenemodule/patientsearch.form")
public class SearchDataController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request){


		return new ModelAndView("module/lucenemodule/patientsearch");
	}
	
	@RequestMapping(value = "/module/lucenemodule/patientsearch", method = RequestMethod.GET)
	public void upload(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}
	
	@RequestMapping( method=RequestMethod.POST)
	public ModelAndView searchIndex(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView("/module/lucenemodule/patientsearch");
		
		SearchRequest searchRequest = SearchMapper.mapFromSearchRequest(request);
		SearchResult searchResult = HttpRequestSenderUtility.searchIndex(searchRequest);
		
		mv.addObject("searchResult", searchResult);	
		
		return mv;
	}


}
