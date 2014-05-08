package org.openmrs.module.lucenemodule.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "module/lucenemodule/search.form")
public class SearchDataController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request){


		return new ModelAndView("module/lucenemodule/search");
	}
	
	@RequestMapping(value = "/module/lucenemodule/search", method = RequestMethod.GET)
	public void upload(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}


}
