package uz.ilmnajot.hotel_management.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestResponseDTO {

    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String phone;
    private Long userDetailsId;
    private Long roleId;
}
