package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.common.ApiResponse;

public interface BookingsService {
    ApiResponse bookRoom(Long roomId, Long userId);
}
