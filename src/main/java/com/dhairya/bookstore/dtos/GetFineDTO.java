package com.dhairya.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFineDTO {
	private FineDTO unpaidFines;
	private FineDTO paidFines;
}
