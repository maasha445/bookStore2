package com.ese.bookworm.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookDTO {
//    @EmbeddedId
//    private OrderBookID orderBookID;

    private int quantity;
    private OrderDTO order;
    private BookDTO book;

}
