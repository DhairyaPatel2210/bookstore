package com.dhairya.bookstore.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dhairya.bookstore.dtos.BorrowerDTO;
import com.dhairya.bookstore.dtos.CheckInSearchDTO;
import com.dhairya.bookstore.dtos.GetFineDTO;
import com.dhairya.bookstore.dtos.LoanDTO;
import com.dhairya.bookstore.dtos.SearchDTO;
import com.dhairya.bookstore.entities.Book;
import com.dhairya.bookstore.entities.Borrower;

@Component
public interface DataStoreService {
    public Boolean storeAuthors();
    public Boolean storeBooks();
    public Boolean storeBorrower();
    public List<SearchDTO> searchBooks(String query);
    public Boolean addBorrower(BorrowerDTO b);
    public String addLoan(LoanDTO loan);
    public String addBookToLoan(String isbn, String card_id);
    public List<CheckInSearchDTO> checkInSearch(String searchQuery);
    public String checkin(CheckInSearchDTO ci);
    public Boolean updateFines();
    public GetFineDTO getFines(String cardId);
    public Boolean updateBooks();
    public String payFine(String cardId);
}
