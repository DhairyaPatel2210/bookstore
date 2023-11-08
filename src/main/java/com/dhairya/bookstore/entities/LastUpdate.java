package com.dhairya.bookstore.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LastUpdate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int updateId;
	private Date lastUpdated;
}
