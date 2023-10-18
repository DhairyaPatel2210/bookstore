package com.dhairya.bookstore.services;

import org.springframework.stereotype.Component;

@Component
public interface DataStoreService {
    public Boolean storeAuthors();
    public Boolean storeBooks();
}
