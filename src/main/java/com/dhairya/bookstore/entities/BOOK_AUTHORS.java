package com.dhairya.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BOOK_AUTHORS {

    @OneToOne(mappedBy = "Author_id")
    private Author Author_id;

    @OneToOne (mappedBy = "Isbn")
    private Book Isbn;
}
