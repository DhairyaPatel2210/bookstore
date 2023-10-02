package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book_Loans {
    @Id
    private Long Loan_id;
    private Long Isbn;
    private Long Card_id;
    private Date Date_out;
    private Date Due_date;
    private Date Date_in;
}
