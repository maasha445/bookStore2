package com.ese.bookworm.services;

import com.ese.bookworm.Factory_dto.ModelToDTO;
import com.ese.bookworm.dtos.BookDTO;
import com.ese.bookworm.dtos.RatingDTO;
import com.ese.bookworm.Entity.Book;
import com.ese.bookworm.Entity.Rating;
import com.ese.bookworm.repositories.BookRepository;
import com.ese.bookworm.repositories.RatingRepository;
import com.ese.bookworm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;

    @Autowired
    RatingRepository ratingRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private ModelToDTO modelToDTO;

    public ResponseEntity<Boolean> addBook(Book newBook){
        Optional<Book> bookOptional=bookRepo.findById(newBook.getId());
        if(!bookOptional.isPresent()){
            bookRepo.save(newBook);
            return new ResponseEntity<>(true, HttpStatus.OK);

        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<BookDTO> getBook(int id){
        Optional<Book> bookOptional = bookRepo.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            return new ResponseEntity<>(modelToDTO.bookToDTO(book),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<BookDTO>> getBookList() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        Iterable<Book> books = bookRepo.findAll();
        for(Book book : books){
            bookDTOList.add(modelToDTO.bookToDTO(book));
        }
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> deleteBook(int id){
        try{
            bookRepo.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    public ResponseEntity<BookDTO> updateBook(int id, Book newBook){
        Optional<Book> bookOptional = bookRepo.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setImagePath(newBook.getImagePath());
            book.setPrice(newBook.getPrice());
            book.setQtyInStock(newBook.getQtyInStock());
            book.setPublicationDate(newBook.getPublicationDate());
            bookRepo.save(book);
            return new ResponseEntity<>(modelToDTO.bookToDTO(book), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

//    public ResponseEntity<List<RatingDTO>> getUserRatingList(String id){
//        Optional<User> userOptional = userRepo.findById(id);
//        if(userOptional.isPresent()){
//            List<RatingDTO> ratingDTOList = new ArrayList<>();
//            List<Rating> ratingList = ratingRepo.findByUser(userOptional.get());
//            for(Rating rating: ratingList){
//                ratingDTOList.add(modelToDTO.ratingToDTO(rating));
//            }
//            return new ResponseEntity<>(ratingDTOList, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

    public ResponseEntity<List<RatingDTO>> getBookRatingList(int id){
        Optional<Book> bookOptional = bookRepo.findById(id);
        if(bookOptional.isPresent()){
            List<RatingDTO> ratingDTOList = new ArrayList<>();
            List<Rating> ratingList = ratingRepo.findByBook(bookOptional.get());
            for(Rating rating: ratingList){
                ratingDTOList.add(modelToDTO.ratingToDTO(rating));
            }
            return new ResponseEntity<>(ratingDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }



//    public ResponseEntity<RatingDTO> addNewRating(Rating newRating){
//        Optional<Book> bookOptional = bookRepo.findById(newRating.getBook().getId());
//        if(bookOptional.isPresent()){
//            newRating.setBook(bookOptional.get());
//            newRating = ratingRepo.save(newRating);
//            return new ResponseEntity<>(modelToDTO.ratingToDTO(newRating), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

//    public ResponseEntity<RatingDTO> updateRating(Rating updateRating){
//        Optional<Rating> ratingOptional = ratingRepo.findById(updateRating.getId());
//        if(ratingOptional.isPresent()){
//            Rating rating = ratingOptional.get();
//            rating.setRating(updateRating.getRating());
//            ratingRepo.save(rating);
//            return new ResponseEntity<>(modelToDTO.ratingToDTO(rating),HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

//    public ResponseEntity<Boolean> deleteRating(int id){
//        try{
//            ratingRepo.deleteById(id);
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } catch (EmptyResultDataAccessException erda_ex){
//
//        }
//        return new ResponseEntity<>(false, HttpStatus.OK);
//    }



}
