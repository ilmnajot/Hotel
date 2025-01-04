package uz.ilmnajot.hotel_management.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.DetailsStatus;

import java.time.LocalDate;

@Setter
@Getter
public class UserDetailsRequestDTO {

    private DetailsStatus detailsStatus;
    private String passportNumber;
    private LocalDate expirationDate;
    private String details;
}
