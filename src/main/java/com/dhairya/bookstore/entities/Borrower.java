package com.dhairya.bookstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Card_id;
    private int Ssn;
    private String Bname;
    private String Address;
    private String Phone;
}
