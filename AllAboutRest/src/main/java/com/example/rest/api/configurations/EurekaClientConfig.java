package com.example.rest.api.configurations;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
public class EurekaClientConfig {

	// This will ensure this service is register as Eureka client

	/*- Now, in order for any other application to call this application 
	 *  they can call on with the application name registered on the 
	 *  eureka server
	 *  
	 *  we have configuration done in the application.yml file 
	 *  for which eureka server to check the instance at
	 *  
	 *  http://all-about-rest/Resources
	 * 
	 */
}
