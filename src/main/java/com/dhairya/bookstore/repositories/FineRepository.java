package com.dhairya.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhairya.bookstore.entities.Book_Loans;
import com.dhairya.bookstore.entities.Fine;

public interface FineRepository extends JpaRepository<Fine, Long>{
	public Fine findByLoan(Book_Loans loan);
	
	@Query("SELECT f FROM Fine f " +
	           "JOIN f.loan l " +
	           "JOIN l.borrower b " +
	           "WHERE b.Card = %:CardId% and f.Paid=false")
	public List<Fine> findUnpaidFines(@Param("CardId") String CardId);
	
	@Query("SELECT f FROM Fine f " +
	           "JOIN f.loan l " +
	           "JOIN l.borrower b " +
	           "WHERE b.Card = %:CardId% AND f.Paid=True")
	public List<Fine> findPaidFines(@Param("CardId") String CardId);
}
