package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.*;
import uz.ilmnajot.hotel_management.enums.RoomType;
import uz.ilmnajot.hotel_management.enums.Status;
import uz.ilmnajot.hotel_management.template.AbsEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rooms")
@Builder
public class Room extends AbsEntity {

    @Column(nullable = false, unique = true)
    private int roomNumber;

    private int floor;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "TEXT")
    private String features;

    @OneToMany
    private List<User> users;

}
