package uz.ilmnajot.hotel_management.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDTO {

    private String email;
    private String password;
}
