package com.ratna.paymentservice.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ratna.paymentservice.entity.Payment;
import com.ratna.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {
	private Logger LOG = LoggerFactory.getLogger(PaymentService.class);
	@Autowired
	private PaymentRepository paymentRepository;

	@Value("${server.port}")
	private String port;

	public Payment doPayment(Payment paymemnt) {
		LOG.info("Payment Service doPayment() request : {}", paymemnt);
		paymemnt.setTransactionId(UUID.randomUUID().toString());
		paymemnt.setPaymentStatus(paymentStatus() + port);
		LOG.info("Successfully executed  from  Payment service: {}", paymemnt.getPaymentId());
		return paymentRepository.save(paymemnt);
	}

	public String paymentStatus() {
		return new Random().nextBoolean() ? "SUCCESS" : "FAILED";
	}

	public Payment findPaymentDetailsWithOrderId(@PathVariable Integer orderId) {
		Payment payment = paymentRepository.findByOrderId(orderId);
		LOG.info("Payment Service findPaymentDetailsWithOrderId() response : {}", payment);
		return payment;
	}
}
