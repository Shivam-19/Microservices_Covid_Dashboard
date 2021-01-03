
package com.dashboard.proxyInterface;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dashboard.model.News;
import com.dashboard.model.State;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClients(value = {@RibbonClient(name = "state-data"), @RibbonClient(name = "news-service")})
public interface Proxy {

	@GetMapping("/state-data/state/stateInfo")
	public State[] retrieveAllStateData();
	
	@GetMapping("/state-data/state/stateInfo/{state}")
	public State[] retrieveStateData(@PathVariable("state") String state);

	@GetMapping("/news-service/GetNews/{q}")
	public News[] RetrieveNews(@PathVariable("q") String q);
	
}
