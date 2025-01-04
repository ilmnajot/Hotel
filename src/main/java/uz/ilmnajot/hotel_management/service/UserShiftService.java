package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.entity.UserShift;

public interface UserShiftService {
    ApiResponse assignShift(Long userId, UserShiftRequestDTO userShiftRequestDTO);

    UserShift getUserShiftId(Long userShiftId);
    UserShift getUserShiftByUserId(Long userId);
}
