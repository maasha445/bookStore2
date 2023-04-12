package com.ese.bookworm;

import com.ese.bookworm.Entity.Book;
import com.ese.bookworm.Factory_dto.ModelToDTO;
import com.ese.bookworm.repositories.BookRepository;
import com.ese.bookworm.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DeleteBookControllerTest {

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
    public void testDeleteBook_Success() throws Exception {
        // Arrange
        int id = 1;
        Book book = new Book();
        book.setId(id);
        when(bookRepo.findById(id)).thenReturn(Optional.of(book));

        // Act
        ResponseEntity<Boolean> response = bookService.deleteBook(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(bookRepo, times(1)).findById(id);
        verify(bookRepo, times(1)).deleteById(id);
    }
//    @Test
//    public void testDeleteBook_Failure() {
//        // Arrange
//        int id = 1;
//        Optional<Book> optionalBook = Optional.empty();
//        when(bookRepo.findById(id)).thenReturn(optionalBook);
//        doNothing().when(bookRepo).deleteById(id);
//
//        // Act
//        ResponseEntity<Boolean> response = bookService.deleteBook(id);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(false, response.getBody());
//        verify(bookRepo, times(1)).findById(id);
//        verify(bookRepo, times(0)).deleteById(id);
//    }

}
