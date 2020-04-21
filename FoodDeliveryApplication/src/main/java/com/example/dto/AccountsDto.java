package com.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto {

	private String accNumber;
	private String bankName;
	private float amount;
	private String phoneNo;
	private int cvv;
	private int userId;
	
}
