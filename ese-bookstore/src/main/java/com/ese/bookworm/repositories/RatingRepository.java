package com.ese.bookworm.repositories;

import com.ese.bookworm.Entity.Book;
import com.ese.bookworm.Entity.Rating;
import com.ese.bookworm.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Integer> {
    //Get all ratings made for a specific book
    List<Rating> findByBook(Book book);

    //Get all ratings made by a specific user
    List<Rating> findByUser(User user);
}
