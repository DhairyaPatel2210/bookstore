package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Borrower {
    @Id
    private Long Card_id;
    private int Ssn;
    private String Bname;
    private String Address;
    private String Phone;
}
