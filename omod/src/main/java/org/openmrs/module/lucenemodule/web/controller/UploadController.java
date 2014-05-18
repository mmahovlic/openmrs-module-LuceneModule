package org.openmrs.module.lucenemodule.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.lucenemodule.web.controller.model.IndexingResult;
import org.openmrs.module.lucenemodule.web.controller.util.HttpRequestSenderUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "module/lucenemodule/upload.form")
public class UploadController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request){


		return new ModelAndView("module/lucenemodule/upload");
	}
	
	@RequestMapping(value = "/module/lucenemodule/upload", method = RequestMethod.GET)
	public void upload(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}
	
	@RequestMapping( method=RequestMethod.POST)
	public ModelAndView saveDataFromFile(@RequestParam("file") MultipartFile file) throws Exception{
		
		ModelAndView mv = new ModelAndView("/module/lucenemodule/upload");
		
		IndexingResult indexingResult =HttpRequestSenderUtility.indexFile(file);

		mv.addObject("indexingResult", indexingResult);	
		
		return mv;
	}
	

}
