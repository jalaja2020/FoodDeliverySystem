package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CustomerDetails;
@Repository
public interface CustomerDetailsRepository  extends JpaRepository<CustomerDetails, Integer>{
		
}
