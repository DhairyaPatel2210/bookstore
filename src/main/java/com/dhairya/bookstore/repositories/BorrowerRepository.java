package com.dhairya.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhairya.bookstore.entities.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, String> {
}
