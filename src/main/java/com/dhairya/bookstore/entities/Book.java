package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Book {
    @Id
    private Long Isbn;
    private String Title;
    private String Cover;
    private int Page;
    private String Publisher;
}
