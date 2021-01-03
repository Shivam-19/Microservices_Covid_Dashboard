package com.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dashboard.model.News;
import com.dashboard.model.State;
import com.dashboard.proxyInterface.Proxy;
import com.dashboard.service.WebClientService;

@RestController
@RequestMapping("/v1/India")
public class Controller {

	@Autowired
	private WebClientService service;
	
	
	@Autowired
	private Proxy proxy;
	
	@RequestMapping(method = RequestMethod.GET, value="/dashboard")
	public ModelAndView mapBView() {
		ModelAndView mv =  new ModelAndView("covid-dashboard");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getsummary")
	public ResponseEntity<Object> getACD() {
		 return new ResponseEntity<Object>(service.getSummaryNumbers(), HttpStatus.OK);
	}

	
	  @RequestMapping(value = "/stateInfo", method = RequestMethod.GET) 
	  public State[] getIndiaMap() {
		return proxy.retrieveAllStateData();
	  }
	 
	  @RequestMapping(value = "/stateInfo/{state}", method = RequestMethod.GET)
	  public State[] data(@PathVariable String state) {
		  return proxy.retrieveStateData(state);
	  }
	  
	  @GetMapping("/GetNews/{q}")
		public News[] getNews(@PathVariable String q) {
			return proxy.RetrieveNews(q);
			
			
		}
}
