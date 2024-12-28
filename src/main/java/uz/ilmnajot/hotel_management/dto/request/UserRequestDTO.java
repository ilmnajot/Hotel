package uz.ilmnajot.hotel_management.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserRequestDTO {

    private String fName;
    private String lName;
    private String email;
    private String phone;
    private UserDetailsRequestDTO userDetailsRequestDTO;
    private UserShiftRequestDTO userShiftRequestDTO;
    private Long roleId;
}
