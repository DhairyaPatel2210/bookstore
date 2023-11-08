package com.dhairya.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerDTO {
	private String name;
	private String ssn;
	private String phone;
	private Address address;
}
