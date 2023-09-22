package com.dhairya.bookstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookStoreController {

    @GetMapping("/test")
    public ResponseEntity<String> testing(){
        return new ResponseEntity<String>("Hi", HttpStatus.OK);
    }

}
