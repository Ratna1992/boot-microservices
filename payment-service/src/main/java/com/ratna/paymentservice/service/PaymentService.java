package com.ratna.paymentservice.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ratna.paymentservice.entity.Payment;
import com.ratna.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment doPayment(Payment paymemnt) {
		paymemnt.setTransactionId(UUID.randomUUID().toString());
		paymemnt.setPaymentStatus(paymentStatus());
		return paymentRepository.save(paymemnt);
	}

	public String paymentStatus() {
		return new Random().nextBoolean() ? "SUCCESS" : "FAILED";
	}

	public Payment findPaymentDetailsWithOrderId(@PathVariable Integer orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
}
