package com.ese.bookworm.controllers;

import com.ese.bookworm.dtos.OrderBookDTO;
import com.ese.bookworm.dtos.OrderDTO;
import com.ese.bookworm.Entity.Order;
import com.ese.bookworm.Entity.OrderBook;
import com.ese.bookworm.Entity.User;
import com.ese.bookworm.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/CreateOrder")
    public ResponseEntity<Boolean> CreateOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PostMapping("/GetOrder")
    public ResponseEntity<OrderDTO> getOrderDetails(@RequestBody Order order){
        return orderService.getOrder(order.getId());
    }

    @PutMapping("/UpdateOrder/{id}")
    public ResponseEntity<OrderDTO> UpdateOrder(@PathVariable int id, @RequestBody Order order){
        return orderService.updateOrder(id,order);
    }

    @PostMapping("/GetCurrentOrder")
    public ResponseEntity<OrderDTO> getCurrentOrderDetails(@RequestBody User user){
        return orderService.getCurrentOrder(user.getEmail());
    }

    @PostMapping("/GetOrderList")
    public ResponseEntity<List<OrderDTO>> getOrderList(){
        return orderService.getOrderList();
    }

    @PostMapping("/GetOrderListByUser")
    public ResponseEntity<List<OrderDTO>> getOrderListByUser(@RequestBody User user){
        return orderService.getOrderListByUser(user.getEmail());
    }

    @PutMapping("/UpdateOrderAddBook/{id}")
    public ResponseEntity<OrderDTO> UpdateOrderAddBook(@PathVariable int id, @RequestBody Order order){
        return orderService.updateOrderAddBook(id,order);
    }

//    @PutMapping("/UpdateOrderCheckOut/{id}")
//    public ResponseEntity<OrderDTO> UpdateOrderCheckOut(@PathVariable int id, @RequestBody Order order){
//        return orderService.updateOrderCheckOut(id,order);
//    }

    @PutMapping("/updateBookQuantity/{action}")
    public ResponseEntity<OrderBookDTO> UpdateBookQuantity(@PathVariable String action, @RequestBody OrderBook order){
        return orderService.updateBookQuantity(action,order);
    }

    @PostMapping("/CreateOrderBook")
    public ResponseEntity<Boolean> CreateOrderBook(@RequestBody OrderBook orderBook){
        return orderService.createOrderBook(orderBook);
    }

    @DeleteMapping("/DeleteOrderBook/{orderId}/{bookId}")
    public ResponseEntity<Boolean> DeleteOrderBook(@PathVariable int orderId, @PathVariable int bookId ){
        return orderService.deleteOrderBook(orderId,bookId);
    }
}
