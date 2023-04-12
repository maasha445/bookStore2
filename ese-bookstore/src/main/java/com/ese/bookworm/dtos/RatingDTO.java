package com.ese.bookworm.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private int id;
    private int rating;
    private String ratingDesc;
    private UserDTO user;
    private BookDTO book;
}
