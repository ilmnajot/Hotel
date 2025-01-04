package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.PaymentRequestDTO;

public interface PaymentService {
    ApiResponse makePayment(PaymentRequestDTO paymentRequestDTO);
}
