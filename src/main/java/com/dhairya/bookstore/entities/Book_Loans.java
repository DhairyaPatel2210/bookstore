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

    @ManyToOne
    @JoinColumn(name = "Isbn")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "Card_id")
    private Borrower borrower;

    private Date Date_out;
    private Date Due_date;
    private Date Date_in;
    
	public Book_Loans(Book book, Borrower borrower, Date date_out, Date due_date, Date date_in) {
		super();
		this.book = book;
		this.borrower = borrower;
		Date_out = date_out;
		Due_date = due_date;
		Date_in = date_in;
	}
    
    
}
