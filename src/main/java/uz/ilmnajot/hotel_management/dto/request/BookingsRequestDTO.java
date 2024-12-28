package uz.ilmnajot.hotel_management.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.BookingStatus;

import java.time.LocalDate;

@Setter
@Getter
public class BookingsRequestDTO {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private Long userId;
    private Long roomId;

}
