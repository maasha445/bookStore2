package com.ese.bookworm.repositories;

import com.ese.bookworm.Entity.Order;
import com.ese.bookworm.Entity.OrderBook;
import com.ese.bookworm.Entity.OrderBookID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends CrudRepository<OrderBook, OrderBookID> {
    //Get all the books from a specific order
    List<OrderBook> findByOrder(Order order);
}
