package com.dhairya.bookstore.controllers;

import com.dhairya.bookstore.dtos.AddToLoanDTO;
import com.dhairya.bookstore.dtos.BorrowerDTO;
import com.dhairya.bookstore.dtos.CheckInSearchDTO;
import com.dhairya.bookstore.dtos.GetFineDTO;
import com.dhairya.bookstore.dtos.LoanDTO;
import com.dhairya.bookstore.dtos.ResponseDTO;
import com.dhairya.bookstore.dtos.Search;
import com.dhairya.bookstore.dtos.SearchDTO;
import com.dhairya.bookstore.entities.Book;
import com.dhairya.bookstore.services.DataStoreService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class BookStoreController {

    @Autowired
    private DataStoreService storeService;

    @GetMapping("/test")
    public ResponseEntity<String> testing(){
        return new ResponseEntity<String>("Hi", HttpStatus.OK);
    }
    
    @GetMapping("/updatebooks")
    public ResponseEntity<String> updateBooks(){
        storeService.updateBooks();
    	return new ResponseEntity<String>("Books updated", HttpStatus.OK);
    }

    @GetMapping("/storeauthors")
    public ResponseEntity<String> StoreAuthors(){
        if (storeService.storeAuthors()){
            return new ResponseEntity<String>("Added All the authors", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error generated while adding authors", HttpStatus.OK);
        }
    }

    @GetMapping("/storebooks")
    public ResponseEntity<String> StoreBooks(){
        if (storeService.storeBooks()){
            return new ResponseEntity<String>("Added All the authors", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error generated while adding books", HttpStatus.OK);
        }
    }
    
    @GetMapping("/storeborrowers")
    public ResponseEntity<String> StoreBorrowers(){
        if (storeService.storeBorrower()){
            return new ResponseEntity<String>("Added All the borrowers", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error generated while adding borrowers", HttpStatus.OK);
        }
    }
    
    @PostMapping("/search")
    public ResponseEntity<ResponseDTO<?>> searchBooks(@RequestBody Search s){
    	List<SearchDTO> books = storeService.searchBooks(s.getQuery());
    	ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
    	res.setMessage("Books Found");
    	res.setStatus(200);
    	res.setObject(books);
        return new ResponseEntity<ResponseDTO<?>>(res,HttpStatus.OK);
    }
    
    @PostMapping("/checkinsearch")
    public ResponseEntity<ResponseDTO<?>> searchCheckin(@RequestBody Search s){
    	List<CheckInSearchDTO> books = storeService.checkInSearch(s.getQuery());
    	ResponseDTO<List<CheckInSearchDTO>> res = new ResponseDTO<>();
    	res.setMessage("Results Found");
    	res.setStatus(200);
    	res.setObject(books);
        return new ResponseEntity<ResponseDTO<?>>(res,HttpStatus.OK);
    }
    
    @PostMapping("/addborrower")
    public ResponseEntity<ResponseDTO<?>> addBorrower(@RequestBody BorrowerDTO borrower){
    	if (storeService.addBorrower(borrower)){
    		ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
        	res.setMessage("Added borrower");
        	res.setStatus(200);
        	res.setObject(null);
            return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
        }
        else{
        	ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
        	res.setMessage("Duplicate borrower found");
        	res.setStatus(400);
        	res.setObject(null);
            return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
        }
    }
    
    @PostMapping("/checkin")
    public ResponseEntity<ResponseDTO<?>> checkin(@RequestBody CheckInSearchDTO ci){
    	ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
    	res.setMessage(storeService.checkin(ci));
    	res.setStatus(200);
    	res.setObject(null);
        return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
    }
    
    @PostMapping("/addloans")
    public ResponseEntity<ResponseDTO<?>> addLoans(@RequestBody LoanDTO loan){
    	ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
    	res.setMessage(storeService.addLoan(loan));
    	res.setStatus(200);
    	res.setObject(null);
        return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
    }
    
    @PostMapping("/addbooktoloan")
    public ResponseEntity<ResponseDTO<?>> addBookToLoan(@RequestBody AddToLoanDTO loan){
    	ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
    	storeService.addBookToLoan(loan.getCardId(),loan.getIsbn());
    	res.setMessage("Added");
    	res.setStatus(200);
    	res.setObject(null);
        return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
    }
    
    @GetMapping("/updatefines")
    public ResponseEntity<ResponseDTO<?>> updateFines(){
    	ResponseDTO<List<SearchDTO>> res = new ResponseDTO<>();
    	if (storeService.updateFines()){
        	res.setMessage("All Fines Updated Successfully!");
        	res.setStatus(200);
        	res.setObject(null);
            return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
        }
        else{
        	res.setMessage("Error while updating fines");
        	res.setStatus(400);
        	res.setObject(null);
            return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
        }
    }
    
    @PostMapping("/getfines")
    public ResponseEntity<ResponseDTO<?>> getFines(@RequestBody AddToLoanDTO card){
    	ResponseDTO<GetFineDTO> res = new ResponseDTO<>();
    	GetFineDTO foundFine = storeService.getFines(card.getCardId());
    	res.setMessage("Added");
    	res.setStatus(200);
    	res.setObject(foundFine);
        return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);
    }
    
    @PostMapping("/payfine")
    public ResponseEntity<ResponseDTO<?>> payFine(@RequestBody AddToLoanDTO card){
    	ResponseDTO<GetFineDTO> res = new ResponseDTO<>();
		res.setMessage(storeService.payFine(card.getCardId()));
    	res.setStatus(200);
    	res.setObject(null);
        return new ResponseEntity<ResponseDTO<?>>(res, HttpStatus.OK);    	
    }
    
    

}
