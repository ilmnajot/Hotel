package uz.ilmnajot.hotel_management.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.dto.request.UserDetailsRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;

@Setter
@Getter
public class UserResponseDTO {

    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private Long userDetailsId;
    private Long userShiftId;
    private Long roleId;
}
