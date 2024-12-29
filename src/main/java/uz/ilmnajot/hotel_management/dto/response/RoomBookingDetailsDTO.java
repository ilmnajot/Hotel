package uz.ilmnajot.hotel_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.RoomType;
import uz.ilmnajot.hotel_management.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomBookingDetailsDTO {

    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private BigDecimal price;
    private Status status;
    private String features;
    private String fName;
    private String lName;
    private String email;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;


}
