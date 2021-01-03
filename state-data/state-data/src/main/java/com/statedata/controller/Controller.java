package com.statedata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.statedata.model.District;
import com.statedata.model.State;
import com.statedata.service.WebClientService;


@RestController
@RequestMapping("state")
public class Controller {

	@Autowired
	private WebClientService service ;
	
	@RequestMapping(value = "/stateInfo", method = RequestMethod.GET)
	public State[] getIndiaMap(){
		return service.getStateNumbers();
	}
	
	@RequestMapping(value = "/stateInfo/{state}", method = RequestMethod.GET)
	public  List<District> data(@PathVariable String state){
		return service.getDistrictsInfoForState(state);
	}
}
