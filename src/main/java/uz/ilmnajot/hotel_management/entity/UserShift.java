package uz.ilmnajot.hotel_management.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserShift extends AbsEntity {

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Schema(type = "String", pattern = "HH:mm")
    private LocalTime startTime;
    @Schema(type = "String", pattern = "HH:mm")
    private LocalTime endTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;


}
