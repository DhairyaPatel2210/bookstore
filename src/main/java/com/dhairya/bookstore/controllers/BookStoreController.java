package com.dhairya.bookstore.controllers;

import com.dhairya.bookstore.services.DataStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookStoreController {

    @Autowired
    private DataStoreService storeService;

    @GetMapping("/test")
    public ResponseEntity<String> testing(){
        return new ResponseEntity<String>("Hi", HttpStatus.OK);
    }

    @GetMapping("/storeauthors")
    public ResponseEntity<String> StoreAuthors(){
        if (storeService.storeAuthors()){
            return new ResponseEntity<String>("Added All the authors", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error generated while adding users", HttpStatus.OK);
        }
    }

    @GetMapping("/storebooks")
    public ResponseEntity<String> StoreBooks(){
        if (storeService.storeAuthors()){
            return new ResponseEntity<String>("Added All the authors", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error generated while adding users", HttpStatus.OK);
        }
    }

}
