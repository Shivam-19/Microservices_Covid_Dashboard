package com.dashboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

	private final WebClient webClient;

	public WebClientService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
		
	}
	
	public Object getSummaryNumbers() {
		Object object = this.webClient.get().uri("https://corona.lmao.ninja/v2/countries/India").retrieve()
							.bodyToMono(Object.class).block();
		return object;
		
	}

}
