package com.newsservice.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.News.News;

@Service
public class NewsService {
	
	private final WebClient webClient;

	public NewsService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	@SuppressWarnings("rawtypes")
	private Map getNews(String q) {
		return this.webClient.get().uri("https://newsapi.org/v2/everything?apiKey=208fc0b9d24d4958b371e4e6100f6167&q="+q)
						.retrieve().bodyToMono(Map.class).block();

	}
	
	@SuppressWarnings("rawtypes")
	public News[] getNewsForEverything(String q){
		
		Map newsMap = getNews(q);
		
		final ObjectMapper mapper = new ObjectMapper(); 
		News[] news = mapper.convertValue(newsMap.get("articles"), News[].class);
		  
		return news;
		
	}
}
