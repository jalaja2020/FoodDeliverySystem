package com.example.service;

import java.util.List;

import com.example.dto.CustomerDto;
import com.example.entity.CustomerDetails;
import com.example.entity.Items;

public interface FoodDeliveryService {
	
	public CustomerDetails customerRegistration(CustomerDto customerDto);
	public List<Items> searchVendor(int vendorId);
	public List<Items> findByItems(List<Integer> itemIds);
	
	
}
