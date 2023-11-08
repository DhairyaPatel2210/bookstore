package com.dhairya.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhairya.bookstore.entities.Book;
import com.dhairya.bookstore.entities.Book_Loans;
import com.dhairya.bookstore.entities.Borrower;

public interface BookLoanRepository extends JpaRepository<Book_Loans, Long> {
	
	@Query("SELECT e FROM Book_Loans e JOIN e.borrower b WHERE e.Date_in IS NULL AND b.Card = %:cardId%")
	public List<Book_Loans> findAllByBorrowerAndDate_inIsNull(@Param("cardId") String cardId);
	
	@Query("SELECT b FROM Book_Loans b " +
	           "JOIN b.borrower borrower " +
	           "JOIN b.book book " +
	           "WHERE (book.Isbn = %:isbn% AND borrower.Card = %:cardid%) and b.Date_in IS NULL")
	public Book_Loans lookByBookAndBorrower(@Param("isbn") String book,@Param("cardid") String borrower);
	
	@Query("SELECT b FROM Book_Loans b " +
	           "JOIN b.borrower borrower " +
	           "JOIN b.book book " +
	           "WHERE (book.Isbn = %:searchTerm% OR borrower.name LIKE %:searchTerm% OR borrower.Card = %:searchTerm%) and b.Date_in IS NULL")
	List<Book_Loans> searchByQuery(@Param("searchTerm") String searchTerm);

}
