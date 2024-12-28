package uz.ilmnajot.hotel_management.dto.request;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class UserShiftRequestDTO {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private Long userId;
}
