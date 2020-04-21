package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CustomerDto;
import com.example.entity.CustomerDetails;
import com.example.entity.Items;
import com.example.repository.CustomerDetailsRepository;
import com.example.repository.ItemsRepository;

@Service
public class FoodDeliveryServiceImpl implements FoodDeliveryService{

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	ItemsRepository itemsRepository;
	
	@Override
	public CustomerDetails customerRegistration(CustomerDto customerDto) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustName(customerDto.getCustName());
		customerDetails.setPhoneNo(customerDto.getPhoneNumber());
		return customerDetailsRepository.save(customerDetails);
	}

	@Override
	public List<Items> searchVendor(int vendorId) {
		List<Items> listItems = new ArrayList<Items>();
		 Optional<List<Items>> findByVid = itemsRepository.findByVid(vendorId);
		 if(findByVid.isPresent()) {
			 listItems = findByVid.get();
		 }
		return listItems;
	}

	@Override
	public List<Items> findByItems(List<Integer> itemIds) {
		return itemsRepository.findByItemIds(itemIds);
	}
	

	
}
