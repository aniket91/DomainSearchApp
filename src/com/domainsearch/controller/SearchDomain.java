package com.domainsearch.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domainsearch.index.DomainIndexer;
import com.google.gson.Gson;

@Controller
public class SearchDomain {
	
	@Autowired
	DomainIndexer domainIndexer;
	
	Gson gson = new Gson();
	
	@RequestMapping("/search.html")
	public ModelAndView helloWorld() {
		return new ModelAndView("search");
	}
	
	@RequestMapping(value = "/search/{searchTerm}/end", method = RequestMethod.GET)
	public @ResponseBody void searchDomains(
	        @PathVariable("searchTerm") String searchTerm, 
	        HttpServletRequest request, 
	        HttpServletResponse response) {
		
		Object[] results = new String[] {};
		
		System.out.println("searchTerm : "  + searchTerm);
		
		if(searchTerm != null && searchTerm.length() > 0) {
			results = domainIndexer.getMatchedDomains(searchTerm);
		}
		
		

		try {
			response.getOutputStream().write(gson.toJson(results).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
