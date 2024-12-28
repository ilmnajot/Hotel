package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;
import uz.ilmnajot.hotel_management.enums.PaymentMethod;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Payment extends AbsEntity {

    @OneToOne
    private Bookings bookings;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


}
