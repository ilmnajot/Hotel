package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.repository.PaymentRepository;
import uz.ilmnajot.hotel_management.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
