package com.ratna.os.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ratna.os.entity.Order;
import com.ratna.os.pojo.Payment;
import com.ratna.os.pojo.TransactionRequest;
import com.ratna.os.pojo.TransactionResponse;
import com.ratna.os.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {

	private Logger LOG = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	@Lazy
	RestTemplate template;

	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String paymentURL;

	public TransactionResponse saveOrder(TransactionRequest transactionRequest) {
		LOG.info("Order Service request : {}", transactionRequest);
		String message = "";
		TransactionResponse transactionResponse = new TransactionResponse();
		Order orderRequest = transactionRequest.getOrder();
		Order order = orderRepository.save(transactionRequest.getOrder());
		Payment payment = new Payment();
		payment.setAmt(orderRequest.getPrice());
		payment.setOrderId(order.getId());
		Payment postForEntity = template.postForObject(paymentURL, payment, Payment.class);
		LOG.info("Payment Rest Call from Order Service : {}", postForEntity);
		message = postForEntity.getPaymentStatus().equalsIgnoreCase("SUCCESS") ? "Successful transaction"
				: "failed transction";
		transactionResponse.setOrder(order);
		transactionResponse.setPayment(postForEntity);
		transactionResponse.setMessage(message);
		LOG.info("Successfully executed  from Order Service : {}", transactionResponse);
		return transactionResponse;
	}
}
