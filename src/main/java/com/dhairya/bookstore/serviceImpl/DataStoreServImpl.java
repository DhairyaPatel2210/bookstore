package com.dhairya.bookstore.serviceImpl;

import com.dhairya.bookstore.dtos.BookDTO;
import com.dhairya.bookstore.dtos.BorrowerDTO;
import com.dhairya.bookstore.dtos.CheckInSearchDTO;
import com.dhairya.bookstore.dtos.FineDTO;
import com.dhairya.bookstore.dtos.GetFineDTO;
import com.dhairya.bookstore.dtos.LoanDTO;
import com.dhairya.bookstore.dtos.SearchDTO;
import com.dhairya.bookstore.entities.Author;
import com.dhairya.bookstore.entities.Book;
import com.dhairya.bookstore.entities.Book_Loans;
import com.dhairya.bookstore.entities.Borrower;
import com.dhairya.bookstore.entities.Fine;
import com.dhairya.bookstore.entities.LastUpdate;
import com.dhairya.bookstore.repositories.AuthorRepository;
import com.dhairya.bookstore.repositories.BookLoanRepository;
import com.dhairya.bookstore.repositories.BookRepository;
import com.dhairya.bookstore.repositories.BorrowerRepository;
import com.dhairya.bookstore.repositories.FineRepository;
import com.dhairya.bookstore.repositories.LastUpdateRepository;
import com.dhairya.bookstore.services.DataStoreService;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DataStoreServImpl implements DataStoreService {

    @Autowired
    private AuthorRepository authorRepo;
    
    @Autowired
    private BookRepository bookRepo;
    
    @Autowired
    private BorrowerRepository borrowerRepo;
    
    @Autowired
    private BookLoanRepository loanRepo; 
    
    @Autowired
    private FineRepository fineRepo;
    
    @Autowired
    private LastUpdateRepository lastUpdateRepo;
    
    @Override
    public Boolean storeAuthors() {
        String filepath = "D:\\UTD\\3.Database Design(6360)\\Projects\\Ind_Project_1\\authors.csv";
        try{
            File initialFile = new File(filepath);
            InputStream targetStream = new FileInputStream(initialFile);
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(targetStream, "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    Author author = new Author(csvRecord.get("Author"));
                    authorRepo.save(author);
                }

                return true;
            }catch(Exception e){
                System.out.println(e);
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean storeBooks() {
        String filepath = "D:\\UTD\\3.Database Design(6360)\\Projects\\Ind_Project_1\\updated_books.csv";
        try{
            File initialFile = new File(filepath);
            InputStream targetStream = new FileInputStream(initialFile);
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(targetStream, "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

                Iterable<CSVRecord> csvRecords = csvParser.getRecords();
                for (CSVRecord csvRecord : csvRecords) {
                	String authorlist[] =  csvRecord.get("Author").replace("\"", "").replace(";", ",").split(",");
                	String bookTitle = csvRecord.get("Title");
                	String bookIsbn = csvRecord.get("ISBN10");
                	String bookCover = csvRecord.get("Cover");
                	int bookPage = Integer.parseInt(csvRecord.get("Pages"));
                	String bookPublisher = csvRecord.get("Publisher");
                	List<Author> authors = new ArrayList<Author>();
                    for(String str : authorlist) {
                    	Author a = authorRepo.findByName(str);
                    	authors.add(a);
                    }
                    Book b = new Book(bookIsbn, bookTitle, bookCover, bookPage, bookPublisher,"Available",authors);
                    bookRepo.save(b);
                }
                return true;
            }catch(Exception e){
                System.out.println(e);
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

	@Override
	public List<SearchDTO> searchBooks(String query) {
		if (!query.trim().isEmpty()) {
			List<Book> books = bookRepo.searchBooksByTitleOrAuthorName(query.trim());
			List<SearchDTO> searchList = new ArrayList<SearchDTO>();
			for(Book book : books) {
				List<Author> authors = book.getAuthor();
				String authorString = "";
				for(Author a: authors) {
					authorString += a.getName() + ","; 
				}
				SearchDTO s = new SearchDTO(book.getIsbn(),book.getTitle(),authorString,book.getAvailability());
				searchList.add(s);
			}
			return searchList;
		}else {
			return new ArrayList<SearchDTO>();
		}
		
	}

	@Override
	public Boolean storeBorrower() {
		String filepath = "D:\\UTD\\3.Database Design(6360)\\Projects\\Ind_Project_1\\borrowers.csv";
        try{
            File initialFile = new File(filepath);
            InputStream targetStream = new FileInputStream(initialFile);
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(targetStream, "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

                Iterable<CSVRecord> csvRecords = csvParser.getRecords();
                for (CSVRecord csvRecord : csvRecords) {
                	String card_id = csvRecord.get("ID0000id");
                	int ssn = Integer.parseInt(csvRecord.get("ssn").replace("-", ""));
                	String name = csvRecord.get("first_name") + " " +csvRecord.get("last_name");
                	String address = csvRecord.get("address") + "," +csvRecord.get("city") + "," + csvRecord.get("state");
                	String phone = csvRecord.get("phone").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
                	
                    Borrower b = new Borrower(card_id,ssn, name, address, phone);
                    borrowerRepo.save(b);
                }
                return true;
            }catch(Exception e){
                System.out.println(e);
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
	}

	@Override
	public Boolean addBorrower(BorrowerDTO b) {
		try {
			String prefix = "ID";
			long count = borrowerRepo.count()+1;
			StringBuilder value = new StringBuilder(Long.toString(count));
	        int len = 6 - value.length();
	        while(len>0) {
	        	value.insert(0, '0');
	        	len-=1;
	        }
	        String id = prefix + value.toString();
	    	int ssn = Integer.parseInt(b.getSsn());
	    	String name = b.getName();
	    	String address = b.getAddress().getStreet() + "," +b.getAddress().getCity() + "," + b.getAddress().getState();
	    	String phone = b.getPhone();
	    	
	        Borrower br = new Borrower(id,ssn, name, address, phone);
	        borrowerRepo.save(br);
	        return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String addLoan(LoanDTO loan) {
		String cardId = loan.getCard_id();
		List<BookDTO> books = loan.getBooks();
		try {
			Borrower b = borrowerRepo.findById(cardId).get();
			List<Book_Loans> allLoans = loanRepo.findAllByBorrowerAndDate_inIsNull(b.getCard());
			if(allLoans.size() + books.size()>3) {
				return "You can borrow only " + (3 - allLoans.size()) + " books!";
			}else {
				for(BookDTO bd : books) {
					String msg = addBookToLoan(cardId, bd.getIsbn());
					if(msg=="") {
						continue;
					}else {
						return msg;
					}
				}
				return  books.size() + " Book(s) Added Under Card ID: " + cardId;
			}
		} catch (Exception e) {
			return "Error while finding borrower with given Card Id";
		}
	}

	@Override
	public String addBookToLoan(String card_id, String isbn) {
		try {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE,14);
			Book bookToLoan = bookRepo.findById(isbn).get();
			this.updateFines();
			Double fineAmt = this.calculateFine(fineRepo.findUnpaidFines(card_id));
			if(fineAmt > 0.0) {
				return "Please pay your remaining fine of " + fineAmt + "$";
			}
			if (bookToLoan.getAvailability().equals("Available")) {
				Book_Loans bl = new Book_Loans(bookToLoan, borrowerRepo.findById(card_id).get(),date,c.getTime(),null);
				bookToLoan.setAvailability("Not Available");
				bookRepo.save(bookToLoan);
				loanRepo.save(bl);
				return "";
			}else {
				return "Book with Isbn: " + isbn + " already taken";
			}
			
		} catch (Exception e) {
			return "Error generated while adding books to loan";
		}
	}

	@Override
	public List<CheckInSearchDTO> checkInSearch(String searchQuery) {
		try {
			if(!searchQuery.trim().isEmpty()) {
				List<Book_Loans> bList= loanRepo.searchByQuery(searchQuery.trim());
				List<CheckInSearchDTO> checkIn = new ArrayList<CheckInSearchDTO>();
				for(Book_Loans book_Loan : bList) {
					CheckInSearchDTO cin = new CheckInSearchDTO(book_Loan.getBook().getIsbn(), book_Loan.getBorrower().getCard(), book_Loan.getBorrower().getName()); 
					checkIn.add(cin);
				}
				return checkIn;
			}
			else {
				return new ArrayList<CheckInSearchDTO>();  
			}
			
		} catch (Exception e) {
			return new ArrayList<CheckInSearchDTO>(); 
		}
	}

	@Override
	public String checkin(CheckInSearchDTO ci) {
		try {
			Date date = new Date();
			Book bookToLoan = bookRepo.findById(ci.getIsbn()).get();
			if (bookToLoan.getAvailability().equals("Not Available")) {
				Book_Loans bl = loanRepo.lookByBookAndBorrower(ci.getIsbn(), ci.getCardId());
				bl.setDate_in(date);
				loanRepo.save(bl);
				bookToLoan.setAvailability("Available");
				bookRepo.save(bookToLoan);
				return "The Book(ISBN:" + ci.getIsbn() + ") has been checkedIn";
			}else {
				return "There is no match for given combination";
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return "Error generated while checking in books";
		}
	}

	@Override
	public Boolean updateFines() {
		try {
			List<Book_Loans> bList = loanRepo.findAll();
			Date currDate = new Date();
			for(Book_Loans bl : bList) {
				if(currDate.compareTo(bl.getDue_date()) > 0) {
					if(bl.getDate_in()!=null && bl.getDate_in().compareTo(bl.getDue_date()) < 0) {
						continue;
					}
					Fine f = fineRepo.findByLoan(bl);
					if(f != null){
						if(!f.getPaid()) {
							f.setFine_amt(this.findFine(bl));
						}
						fineRepo.save(f);
					}
					else {
						Fine f1 = new Fine(bl,this.findFine(bl),false);
						fineRepo.save(f1);
					}	
				}
			}
			Optional<LastUpdate> lu = lastUpdateRepo.findById(1);
			if (lu.isPresent()) {
				lu.get().setLastUpdated(new Date());
				lastUpdateRepo.save(lu.get());
			}else {
				lastUpdateRepo.save(new LastUpdate(1,new Date()));
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private Double findFine(Book_Loans bl) {
		Date currDate = new Date();
		LocalDate localDate1;
		LocalDate localDate2;
		if(bl.getDate_in() == null) {
			localDate1 = currDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			localDate2 = bl.getDue_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}else {
			localDate1 = bl.getDate_in().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			localDate2 = bl.getDue_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
        // Calculate the difference in days
        long dayDifference = ChronoUnit.DAYS.between(localDate2, localDate1);
        Double fineAmt = dayDifference * 0.25;
        return Math.round(fineAmt * 100.0) / 100.0;
	}
	
	

	@Override
	public GetFineDTO getFines(String cardId) {
		try {
			Date lastUpdatedDate = lastUpdateRepo.findById(1).get().getLastUpdated();
			Date currDate = new Date();
			if(currDate.compareTo(lastUpdatedDate) != 0) {
				this.updateFines();
			}
			List<Fine> unpaidFines=fineRepo.findUnpaidFines(cardId);
			List<Fine> paidFines=fineRepo.findPaidFines(cardId);
			GetFineDTO fine = new GetFineDTO();
			fine.setUnpaidFines(this.getFineDTO(cardId, this.calculateFine(unpaidFines)));
			fine.setPaidFines(this.getFineDTO(cardId, this.calculateFine(paidFines)));
			return fine;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public String payFine(String cardId) {
		try {
			List<Fine> unpaidFines=fineRepo.findUnpaidFines(cardId);
			for(Fine f : unpaidFines) {
				if(f.getLoan().getDate_in()==null) {
					return "You have not returned any of borrowed books";
				}
			}
			for(Fine f: unpaidFines) {
				f.setPaid(true);
			}
			fineRepo.saveAll(unpaidFines);
			return "Payment Successfull";
		} catch (Exception e) {
			System.out.println(e);
			return "Error generated While making Payment";
		}
	}
	
	private Double calculateFine(List<Fine> fines) {
		Double fineAmt = 0.00;
		for(Fine f : fines) {
			fineAmt+=f.getFine_amt();
		}
		return fineAmt;
	}
	
	private FineDTO getFineDTO(String cardId, Double fineAmt) {
		FineDTO unpaidFine = new FineDTO();
		Borrower b = borrowerRepo.findById(cardId).get();
		unpaidFine.setCardId(cardId);
		unpaidFine.setName(b.getName());
		unpaidFine.setFineAmt(fineAmt);
		return unpaidFine;
	}

	@Override
	public Boolean updateBooks() {
		try {
			List<Book> books = bookRepo.findAll();
			for(Book b : books) {
				b.setAvailability("Available");
				bookRepo.save(b);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
    
    
}
