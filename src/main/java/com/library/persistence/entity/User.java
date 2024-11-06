package com.library.persistence.entity;

import com.library.rest.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name", unique = false, nullable = false)
    private String firstName;

    @Column(name = "last_name", unique = false, nullable = false)
    private String lastName;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "passport_number", unique = true, nullable = false)
    private String passportNumber;


    public UserDTO toDTO(){
        UserDTO dto = new UserDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddress(address);
        dto.setPassportNumber(passportNumber);
        return dto;
    }
}
