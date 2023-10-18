package com.dhairya.bookstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book_Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Loan_id;

    @MapsId
    @OneToOne
    private Book Isbn;

    @MapsId
    @OneToOne
    private Borrower borrower;

    private Date Date_out;
    private Date Due_date;
    private Date Date_in;
}
