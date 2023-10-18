package com.dhairya.bookstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Author_id;

    @Column(unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8_bin")
    private String Name;

    public Author(String name) {
        Name = name;
    }
}
