package com.ese.bookworm.Entity;

import com.ese.bookworm.enums.OrderStatus;
//import com.apiit.onceuponabook.enums.PaymentMethod;
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
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchasedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double totalAmount;

//    @Enumerated(EnumType.STRING)
//    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderedBooks;






}
