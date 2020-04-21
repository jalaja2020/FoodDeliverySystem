package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dto.AccountsDto;
import com.example.dto.FundTransferDto;

@FeignClient(name="http://BANKING-SERVICE/demo/bank")
public interface PaymentServiceClient {
	
	@PostMapping("/paymentDetails")
	public String paymentDetails(@RequestBody FundTransferDto fundTransferDto);
	
	@PostMapping("/registerAccount")
	public String registerAccounts(@RequestBody AccountsDto accountsDto);
}
