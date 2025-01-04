package uz.ilmnajot.hotel_management.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.hotel_management.enums.PaymentMethod;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentRequestDTO {

    private Long bookingId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;

}
