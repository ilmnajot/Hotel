package uz.ilmnajot.hotel_management.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.entity.User;

@Setter
@Getter
public class AuthTokenResponse {

    private String token;
    private User user;
}
