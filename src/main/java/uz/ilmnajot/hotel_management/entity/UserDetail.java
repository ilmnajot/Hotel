package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class UserDetail extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private DetailsStatus detailsStatus;
    private String documentNo;
    private LocalDate expirationDate;
    private String details;
}
