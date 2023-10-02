package com.dhairya.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Authors {
    @Id
    private Long Author_id;
    private String Name;
}
