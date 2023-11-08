package com.dhairya.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {
    @Id
    private String Card;
    
    @Column(unique=true)
    private int Ssn;
    private String name;
    private String Address;
    private String Phone;
    
}
