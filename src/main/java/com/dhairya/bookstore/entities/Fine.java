package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Fine {
    @Id
    private Long Loan_id;
    private Double Fine_amt;
    private Boolean Paid;
}
