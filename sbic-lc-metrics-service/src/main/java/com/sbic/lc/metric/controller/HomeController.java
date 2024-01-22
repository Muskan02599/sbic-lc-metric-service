package com.sbic.lc.metric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

@RestController
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	
	@Value("${application.name}")
	private String applicationName;
	
	@Autowired private Environment environment;
    
    @GetMapping("healthcheck")
    public String healthCheck() {
    	LOGGER.debug("Healthcheck requested");
        StringBuilder profile = new StringBuilder();
        profile.append("Application Name: [").append(applicationName).append("]").append(", ");
       // profile.append("Build Version: [").append(buildVersion).append("]").append(", ");
        profile.append("Profile: [");
        for(String activeProfile : environment.getActiveProfiles()) {
            profile.append(activeProfile);
        }
        profile.append("]").append(", ");
        profile.append("Timezone: [").append(TimeZone.getDefault().getDisplayName());
        profile.append("]");
        return "Healthcheck Success | " + profile.toString();
    }
}