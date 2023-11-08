package com.dhairya.bookstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String Isbn;
    private String Title;
    private String Cover;
    private int Page;
    private String Publisher;
    private String availability;
    @ManyToMany
    private List<Author> author;
}
