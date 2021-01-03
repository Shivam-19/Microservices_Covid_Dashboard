package com.newsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.model.News.News;
import com.newsservice.service.NewsService;

@RestController
public class Controller {
	
	@Autowired
	NewsService service ;
	
	public Controller(NewsService service) {
		this.service = service;
	}

	@GetMapping("/GetNews/{q}")
	public News[] getNews(@PathVariable String q) {
		
		News[] response = service.getNewsForEverything(q);
		
		return response;
		
	}

}
