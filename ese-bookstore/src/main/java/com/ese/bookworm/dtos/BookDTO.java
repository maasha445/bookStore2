package com.ese.bookworm.dtos;

import com.ese.bookworm.enums.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int id;
    private long isbn;
    private String publisher;
    private Date publicationDate;
    private String author;
    private String title;
    private double price;
    private String category;

    @Enumerated(EnumType.STRING)
    private BookFormat format;

    private String description;
    private String imagePath;
    private int qtyInStock;

    private List<UserDTO> users;
    private List<OrderBookDTO> orderedBooks;
    private List<RatingDTO> ratings;
}
