package uz.ilmnajot.hotel_management.dto.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.RoomType;
import uz.ilmnajot.hotel_management.enums.Status;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomResponseDTO {

    private Long id;
    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private BigDecimal price;
    private Status status;
    private String features;
}
