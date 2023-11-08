package com.dhairya.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhairya.bookstore.entities.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	@Query("SELECT b FROM Book b " +
	           "JOIN b.author a " +
	           "WHERE b.Title LIKE %:searchTerm% OR a.name LIKE %:searchTerm%")
	List<Book> searchBooksByTitleOrAuthorName(@Param("searchTerm") String searchTerm);
}
