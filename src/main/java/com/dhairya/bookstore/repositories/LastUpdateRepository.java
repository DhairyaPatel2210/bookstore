package com.dhairya.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhairya.bookstore.entities.LastUpdate;

public interface LastUpdateRepository extends JpaRepository<LastUpdate, Integer>{
}
