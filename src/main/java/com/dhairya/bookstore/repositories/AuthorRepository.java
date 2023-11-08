package com.dhairya.bookstore.repositories;

import com.dhairya.bookstore.entities.Author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	public Author findByName(String name);
}
