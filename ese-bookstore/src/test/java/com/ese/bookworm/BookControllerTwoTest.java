package com.ese.bookworm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.ese.bookworm.Entity.Book;
import com.ese.bookworm.Factory_dto.ModelToDTO;
import com.ese.bookworm.dtos.BookDTO;
import com.ese.bookworm.repositories.BookRepository;
import com.ese.bookworm.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class BookControllerTwoTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepo;

    @Mock
    private ModelToDTO modelToDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateBook_Success() {
        // Arrange
        int id = 1;
        Book newBook = new Book();
        newBook.setImagePath("imagePath");
        newBook.setPrice(100);
        newBook.setQtyInStock(10);
        newBook.setPublicationDate(new Date());
        Book book = new Book();
        book.setId(id);
        book.setImagePath("imagePath");
        book.setPrice(100);
        book.setQtyInStock(10);
        book.setPublicationDate(new Date());
        when(bookRepo.findById(id)).thenReturn(Optional.of(book));
        when(bookRepo.save(any(Book.class))).thenReturn(book);
        when(modelToDTO.bookToDTO(book)).thenReturn(new BookDTO());
        // Act
        ResponseEntity<BookDTO> result = bookService.updateBook(id, newBook);
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new BookDTO(), result.getBody());
    }

    @Test
    public void testUpdateBook_BookNotFound() {
        // Arrange
        int id = 1;
        Book newBook = new Book();
        when(bookRepo.findById(id)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<BookDTO> result = bookService.updateBook(id, newBook);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(null, result.getBody());
    }


}
