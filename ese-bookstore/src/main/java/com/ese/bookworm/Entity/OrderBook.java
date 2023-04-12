package com.ese.bookworm.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered_books")
public class OrderBook implements Serializable {

    private static final long serialVersionUID=1L;

    @EmbeddedId
    private OrderBookID orderBookID;

    private int quantity;

    @ManyToOne(cascade = CascadeType.DETACH)
    @MapsId("orderID")
    @JoinColumn(name = "orderID",referencedColumnName = "id")
    private Order order;

    @ManyToOne(cascade = CascadeType.DETACH)
    @MapsId("bookID")
    @JoinColumn(name = "bookID",referencedColumnName = "id")
    private Book book;

}
