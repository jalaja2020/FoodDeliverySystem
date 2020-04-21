package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AccountsDto;
import com.example.dto.CustomerDto;
import com.example.dto.FundTransferDto;
import com.example.entity.CustomerDetails;
import com.example.entity.Items;
import com.example.feign.PaymentServiceClient;
import com.example.service.FoodDeliveryService;

@RestController
public class FoodDeliveryController {
	@Autowired
	FoodDeliveryService foodDeliveryService;
	
	@Autowired
	PaymentServiceClient paymentServiceClient;
	
	@PostMapping("/customerRegistration")
	public CustomerDetails customerRegistration(@RequestBody CustomerDto customerDto) {
		return foodDeliveryService.customerRegistration(customerDto);
	}
	
	@GetMapping("{vendorId}")
	public List<Items> searchVendor(@PathVariable String vendorId){
		return foodDeliveryService.searchVendor(Integer.parseInt(vendorId));
	}
	
	@PostMapping("/paymentDetails")
	public String paymentDetails(@RequestBody FundTransferDto fundTransferDto) {
		return paymentServiceClient.paymentDetails(fundTransferDto);
	}
	
	@PostMapping("/registerAccount")
	public String registerAccounts(@RequestBody AccountsDto accountsDto) {
		return paymentServiceClient.registerAccounts(accountsDto);
	}
	

}