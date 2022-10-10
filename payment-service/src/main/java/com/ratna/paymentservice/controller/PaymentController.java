package com.ratna.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.paymentservice.entity.Payment;
import com.ratna.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Payment paymemnt) {
		return paymentService.doPayment(paymemnt);
	}

	@GetMapping("/{orderId}")
	public Payment findPaymentDetailsWithOrderId(@PathVariable Integer orderId) {
		return paymentService.findPaymentDetailsWithOrderId(orderId);
	}

}
