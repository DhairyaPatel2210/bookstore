package com.dhairya.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
	
	private String street;
	private String city;
	private String zip;
	private String state;
	

}
