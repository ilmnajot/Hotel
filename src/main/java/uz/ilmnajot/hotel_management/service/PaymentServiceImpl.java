package uz.ilmnajot.hotel_management.service;

import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
