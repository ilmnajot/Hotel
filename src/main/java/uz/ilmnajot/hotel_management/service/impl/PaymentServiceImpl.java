package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.PaymentRequestDTO;
import uz.ilmnajot.hotel_management.entity.Bookings;
import uz.ilmnajot.hotel_management.entity.Payment;
import uz.ilmnajot.hotel_management.enums.BookingStatus;
import uz.ilmnajot.hotel_management.repository.BookingsRepository;
import uz.ilmnajot.hotel_management.repository.PaymentRepository;
import uz.ilmnajot.hotel_management.service.BookingsService;
import uz.ilmnajot.hotel_management.service.PaymentService;
import uz.ilmnajot.hotel_management.service.RoomService;
import uz.ilmnajot.hotel_management.service.UserService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final BookingsService bookingsService;
    private final BookingsRepository bookingsRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserService userService, RoomService roomService, BookingsService bookingsService, BookingsRepository bookingsRepository) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.roomService = roomService;
        this.bookingsService = bookingsService;
        this.bookingsRepository = bookingsRepository;
    }

    @Override
    public ApiResponse makePayment(PaymentRequestDTO paymentRequestDTO) {
        Bookings booking = bookingsService.getBookingsById(paymentRequestDTO.getBookingId());
        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            return new ApiResponse("Booking is not in a valid state for payment", false, HttpStatus.BAD_REQUEST);
        }

        Payment payment = new Payment();
        if (paymentRequestDTO.getPaymentMethod()==null){
            return new ApiResponse("Payment method is required", false, HttpStatus.BAD_REQUEST);
        }
        payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());
        if (!booking.getRoom().getPrice().equals(paymentRequestDTO.getAmount())) {
            return new ApiResponse("Invalid payment amount", false, HttpStatus.BAD_REQUEST);
        }
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setBookings(booking);
        paymentRepository.save(payment);

        booking.setBookingStatus(BookingStatus.PAID);
        bookingsRepository.save(booking);
        return new ApiResponse("Payment has been done successfully", true, HttpStatus.OK, payment);
    }
}
