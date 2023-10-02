package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BOOK_AUTHORS {
    @Id
    private Long Author_id;
    private Long Isbn;
}
