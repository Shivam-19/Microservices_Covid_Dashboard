package com.statedata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.statedata.model.District;
import com.statedata.model.State;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {
	
	private final WebClient webClient;

	public WebClientService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}
	
	
	public State[] getStateNumbers() {
		Mono<State[]> monoStateArray = this.webClient.get().uri("https://www.mohfw.gov.in/data/datanew.json")
										.retrieve().bodyToMono(State[].class);
		return monoStateArray.block();
	}
	
	@SuppressWarnings("rawtypes")
	private Map getDistrictNumbers() {
		return this.webClient.get().uri("https://api.covid19india.org/state_district_wise.json")
						.retrieve().bodyToMono(Map.class).block();
	}
	
	@SuppressWarnings("rawtypes")
	public List<District> getDistrictsInfoForState(String stateName){
		List<District> listState = new ArrayList<>();
		District district ;
		String name ;
		Map map = getDistrictNumbers();
		Map stateMap = (Map) map.get(stateName);
		Map districts = (Map) stateMap.get("districtData");
		
		for (Object entry : districts.keySet()) {
		    Map districtMap = (Map) districts.get(entry);
		    int confirmed = (int) districtMap.get("confirmed");
		    name = entry.toString();
		    district = new District(name,name,confirmed);
		    
			listState.add(district);
			
		}
		
		return listState;
		
	}
}
