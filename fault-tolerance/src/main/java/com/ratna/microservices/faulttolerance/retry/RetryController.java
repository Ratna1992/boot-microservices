package com.ratna.microservices.faulttolerance.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/retry")
public class RetryController {

	Logger logger = LoggerFactory.getLogger(RetryController.class);
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/getEmployee/{id}")
	@Retry(name = "getEmployeeRetry", fallbackMethod = "getEmployeeFallback")
	public String getEmployee(@PathVariable("id") String id) {
		logger.info("getEmployee() call starts here");
		ResponseEntity<String> entity = restTemplate
				.getForEntity("http://localhost:9999/employees/" + id, String.class);
		logger.info("Response :" + entity.getStatusCode());
		return entity.getBody();
	}

	public String getEmployeeFallback(Exception e) {
		logger.info("---RESPONSE FROM FALLBACK METHOD---");
		return "SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
	}

}
