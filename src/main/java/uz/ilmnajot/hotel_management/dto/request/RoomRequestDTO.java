package uz.ilmnajot.hotel_management.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.RoomType;
import uz.ilmnajot.hotel_management.enums.Status;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomRequestDTO {

    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private BigDecimal price;
    private Status status;
    private String features;
}
