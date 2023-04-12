package com.ese.bookworm.Entity;

import com.ese.bookworm.enums.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private long isbn;

    private String publisher;
    private Date publicationDate;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    private BookFormat format;

    private String description;
    private String imagePath;

    @Column(nullable = false)
    private int qtyInStock;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<OrderBook> orderedBooks;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;


}
