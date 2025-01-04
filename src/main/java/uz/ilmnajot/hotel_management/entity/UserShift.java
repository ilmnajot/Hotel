package uz.ilmnajot.hotel_management.entity;

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
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;


}
