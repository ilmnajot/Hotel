package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.entity.Bookings;

public interface BookingsService {
    ApiResponse bookRoom(Long roomId, Long userId);
    Bookings getBookingsById(Long bookingsId);
}
