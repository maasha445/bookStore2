package com.ese.bookworm.dtos;

import com.ese.bookworm.enums.UserRole;
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
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private int phoneNo;
    private String address;
    private String password;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private List<OrderDTO> orders;
    private List<BookDTO> books;
    private List<RatingDTO> ratings;
    private String jwtToken;

    public UserDTO(String jwtToken, String firstName, UserRole role) {
        this.jwtToken=jwtToken;
        this.firstName=firstName;
        this.role=role;
    }
}
