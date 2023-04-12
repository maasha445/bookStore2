package com.ese.bookworm;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.junit.Assert.assertEquals;

import com.ese.bookworm.dtos.BookDTO;
import com.ese.bookworm.Entity.Book;
import com.ese.bookworm.repositories.BookRepository;
import com.ese.bookworm.services.BookService;
import com.ese.bookworm.Factory_dto.ModelToDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;



import org.junit.Test;


import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {


    private MockMvc mockMvc;


    private ObjectMapper objectMapper;

    @InjectMocks
    private BookService bookService;

    @Mock
    @Autowired
    private ModelToDTO modelToDTO;


    @Mock
    private BookRepository bookRepo;




    @Test
    public void addBookSuccess() {
        // Arrange
        Book newBook = new Book();
        when(bookRepo.findById(any())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Boolean> result = bookService.addBook(newBook);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(bookRepo).save(newBook);
    }

    @Test
    public void addBookDuplicate() {
        // Arrange
        Book newBook = new Book();
        when(bookRepo.findById(any())).thenReturn(Optional.of(newBook));

        // Act
        ResponseEntity<Boolean> result = bookService.addBook(newBook);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals(false, result.getBody());
        verify(bookRepo).findById(any());
    }

    @Test
    public void getBookSuccess() {
        // Arrange
        Book book = new Book();
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));
        BookDTO bookDTO = new BookDTO();
        when(modelToDTO.bookToDTO(book)).thenReturn(bookDTO);

        // Act
        ResponseEntity<BookDTO> result = bookService.getBook(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(bookDTO, result.getBody());
    }





}


