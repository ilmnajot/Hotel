package uz.ilmnajot.hotel_management.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.BookingStatus;

import java.time.LocalDate;

@Setter
@Getter
public class BookingsResponseDTO {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private Long userId;
    private Long roomId;

}
