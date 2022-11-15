package com.ratna.microservices.faulttolerance.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/circuit-breaker")
public class CircuitBreakerController {
	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/getEmployee/{id}")
	@CircuitBreaker(name = "getEmployeeCB", fallbackMethod = "getAllEmployees")
	public String getEmployee(@PathVariable("id") String id) {
		logger.info("getEmployee() call starts here");
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:9999/employees/" + id,
				String.class);
		logger.info("Response :" + entity.getStatusCode());
		return entity.getBody();
	}

	public String getAllEmployees(Exception e) {
		logger.info("---RESPONSE FROM FALLBACK METHOD Returning all the available employees---");

		return "[{\r\n" + "  \"id\":0,\"name\":\"emp0\",\"age\":\"30\",\"mobile\":\"8499899120\",\"sal\":\"12340\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":1,\"name\":\"emp1\",\"age\":\"31\",\"mobile\":\"8499899121\",\"sal\":\"12341\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":2,\"name\":\"emp2\",\"age\":\"32\",\"mobile\":\"8499899122\",\"sal\":\"12342\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":3,\"name\":\"emp3\",\"age\":\"33\",\"mobile\":\"8499899123\",\"sal\":\"12343\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":4,\"name\":\"emp4\",\"age\":\"34\",\"mobile\":\"8499899124\",\"sal\":\"12344\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":5,\"name\":\"emp5\",\"age\":\"35\",\"mobile\":\"8499899125\",\"sal\":\"12345\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":6,\"name\":\"emp6\",\"age\":\"36\",\"mobile\":\"8499899126\",\"sal\":\"12346\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":7,\"name\":\"emp7\",\"age\":\"37\",\"mobile\":\"8499899127\",\"sal\":\"12347\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":8,\"name\":\"emp8\",\"age\":\"38\",\"mobile\":\"8499899128\",\"sal\":\"12348\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":9,\"name\":\"emp9\",\"age\":\"39\",\"mobile\":\"8499899129\",\"sal\":\"12349\"\r\n"
				+ "},{\r\n"
				+ "  \"id\":10,\"name\":\"emp0\",\"age\":\"40\",\"mobile\":\"8499899130\",\"sal\":\"12350\"\r\n" + "}]";
	}
}
