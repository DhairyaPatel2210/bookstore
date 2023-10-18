package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fine {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Loan_id;
    private Double Fine_amt;
    private Boolean Paid;
}
