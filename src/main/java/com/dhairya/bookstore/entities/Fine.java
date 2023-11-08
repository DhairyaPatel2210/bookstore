package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fineId;
	
	@OneToOne
    @MapsId
    private Book_Loans loan;
    private Double Fine_amt;
    private Boolean Paid;
    
	public Fine(Book_Loans loan, Double fine_amt, Boolean paid) {
		super();
		this.loan = loan;
		Fine_amt = fine_amt;
		Paid = paid;
	}
    
    
}
