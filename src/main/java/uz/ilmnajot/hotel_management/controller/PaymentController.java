package uz.ilmnajot.hotel_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.PaymentRequestDTO;
import uz.ilmnajot.hotel_management.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/make-payment")
    public HttpEntity<ApiResponse> makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        ApiResponse apiResponse = paymentService.makePayment(paymentRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }
}
