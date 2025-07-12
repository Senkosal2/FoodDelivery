package group.fooddelivery.main.dto;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private AddressDTO addressDTO;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
}
