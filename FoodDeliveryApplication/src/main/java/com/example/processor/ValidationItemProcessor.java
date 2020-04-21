package com.example.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.entity.Items;

@Component
public class ValidationItemProcessor implements ItemProcessor<Items, Items> {

	public Items process(Items items) throws Exception {
		System.out.println("Processing Items: " + items);
		return items;
	}
}