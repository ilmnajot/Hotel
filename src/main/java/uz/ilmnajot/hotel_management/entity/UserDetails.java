package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.hotel_management.enums.DetailsStatus;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserDetails extends AbsEntity {

    @OneToOne
    @Column(unique = true, nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private DetailsStatus detailsStatus;

    private String passportNumber;

    private LocalDate expirationDate;

    private String details;
}
