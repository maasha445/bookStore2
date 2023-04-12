package com.ese.bookworm.repositories;

import com.ese.bookworm.enums.OrderStatus;
import com.ese.bookworm.Entity.Order;
import com.ese.bookworm.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    //Get all orders made by a specific user by its status
    List<Order> findByUserAndStatus(User user, OrderStatus orderStatus);

    //Get all orders made by a specific user
    List<Order> findByUser(User user, Sort sort);

    //Get all orders in a specific order status
    List<Order> findByStatus(OrderStatus orderStatus,Sort sort);

}
