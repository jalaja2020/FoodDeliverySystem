package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private String custName;
	private String phoneNumber;
	private String  email;
	private String  bankAccNo;
	private int cvv;
}
