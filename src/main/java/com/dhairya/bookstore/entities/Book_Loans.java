package com.dhairya.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Book_Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Loan_id;

    @OneToOne(mappedBy = "Isbn")
    private Book Isbn;

    @OneToOne(mappedBy = "Card_id")
    private Borrower borrower;

    private Date Date_out;
    private Date Due_date;
    private Date Date_in;
}
