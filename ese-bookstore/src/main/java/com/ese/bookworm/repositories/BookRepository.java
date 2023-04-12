package com.ese.bookworm.repositories;

import com.ese.bookworm.Entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(long isbn);
}
