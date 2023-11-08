package com.dhairya.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {
	private String cardId;
	private String name;
	private Double fineAmt;
}
