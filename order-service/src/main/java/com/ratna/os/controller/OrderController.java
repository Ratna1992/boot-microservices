package com.ratna.os.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.os.pojo.TransactionRequest;
import com.ratna.os.pojo.TransactionResponse;
import com.ratna.os.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/bookOrder")
	public TransactionResponse saveOrder(@RequestBody TransactionRequest order) {
		return orderService.saveOrder(order);
	}
}
