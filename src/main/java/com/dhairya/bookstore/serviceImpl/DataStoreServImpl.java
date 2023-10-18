package com.dhairya.bookstore.serviceImpl;

import com.dhairya.bookstore.entities.Author;
import com.dhairya.bookstore.repositories.AuthorRepository;
import com.dhairya.bookstore.services.DataStoreService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataStoreServImpl implements DataStoreService {

    @Autowired
    private AuthorRepository authorRepo;

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
                    Author author = new Author(csvRecord.get("Name"));
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
        String filepath = "D:\\UTD\\3.Database Design(6360)\\Projects\\Ind_Project_1\\authors.csv";
        try{
            File initialFile = new File(filepath);
            InputStream targetStream = new FileInputStream(initialFile);
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(targetStream, "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    Author author = new Author(csvRecord.get("Name"));
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
}
