package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.ilmnajot.hotel_management.enums.BookingStatus;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bookings")
@Builder
public class Bookings extends AbsEntity {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;
}
