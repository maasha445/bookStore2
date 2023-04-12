package com.ese.bookworm.services;

import com.ese.bookworm.Factory_dto.ModelToDTO;
import com.ese.bookworm.dtos.BookDTO;
import com.ese.bookworm.dtos.RatingDTO;
import com.ese.bookworm.dtos.UserDTO;
import com.ese.bookworm.Entity.Book;
import com.ese.bookworm.Entity.Rating;
import com.ese.bookworm.Entity.User;
import com.ese.bookworm.repositories.BookRepository;
import com.ese.bookworm.repositories.RatingRepository;
import com.ese.bookworm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    RatingRepository ratingRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelToDTO modelToDTO;

    public ResponseEntity<Boolean> registerUser(User newUser){
        Optional<User> userOptional=userRepo.findById(newUser.getEmail());
        if(!userOptional.isPresent()){
            newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
            userRepo.save(newUser);
            return new ResponseEntity<>(true,HttpStatus.OK);

        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<UserDTO> getUser(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return new ResponseEntity<>(modelToDTO.userToDTO(user),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        Iterable<User> userList = userRepo.findAll();
        for(User user : userList){
            userDTOList.add(modelToDTO.userToDTO(user));
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> deleteUser(String id){
        try{
            userRepo.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> updateUser(String id, User newUser){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPhoneNo(newUser.getPhoneNo());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setAddress(newUser.getAddress());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            userRepo.save(user);
            return new ResponseEntity<>(modelToDTO.userToDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<UserDTO> updatePassword(String id, String currentPsw, String newPsw){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(bcryptEncoder.matches(currentPsw, user.getPassword())) {
                user.setPassword(bcryptEncoder.encode(newPsw));
                userRepo.save(user);
                return new ResponseEntity<>(modelToDTO.userToDTO(user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> createWishlistItem(String id, Book wishBook) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            Optional<Book> bookOptional = bookRepo.findById(wishBook.getId());
            Book wishListBook = bookOptional.get();
            List<Book> wishlist = userOptional.get().getBooks();
            boolean isAddded=false;
            if(wishlist.size()==0){
                wishlist.add(wishListBook);
            }else{
                for(Book book: wishlist){
                    if(book.getId()==wishBook.getId()){
                        isAddded=true;
                    }
                }
                if(!isAddded){
                    wishlist.add(wishBook);
                }
            }
            User updateUser =userOptional.get();
            updateUser.setBooks(wishlist);
            userRepo.save(updateUser);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<BookDTO>> getWishList(User user) {
        List<BookDTO> wishlistDTOs = new ArrayList<>();
        Optional<User> userOptional = userRepo.findById(user.getEmail());
        if(userOptional.isPresent()){
            List<Book> wishlist = userOptional.get().getBooks();
            for(Book book:wishlist){
                wishlistDTOs.add(modelToDTO.bookToDTO(book));
            }
        }
        return new ResponseEntity<>(wishlistDTOs, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> createRatingItem(Rating rating) {
        Optional<Rating> ratingOptional=ratingRepo.findById(rating.getId());
        if(!ratingOptional.isPresent()){
            ratingRepo.save(rating);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<RatingDTO>> getRatingList(User user) {
        List<RatingDTO> ratinglistDTOs = new ArrayList<>();
        Optional<User> userOptional = userRepo.findById(user.getEmail());
        if(userOptional.isPresent()){
            List<Rating> ratinglist = userOptional.get().getRatings();
            for(Rating rating:ratinglist){
                ratinglistDTOs.add(modelToDTO.ratingToDTO(rating));
            }
        }
        return new ResponseEntity<>(ratinglistDTOs, HttpStatus.OK);
    }





}