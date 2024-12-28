package uz.ilmnajot.hotel_management.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.DetailsStatus;

import java.time.LocalDate;

@Setter
@Getter
public class UserDetailsResponseDTO {

    private Long id;
    private DetailsStatus detailsStatus;
    private String passportNumber;
    private LocalDate expirationDate;
    private String details;
    private Long userId;
}
