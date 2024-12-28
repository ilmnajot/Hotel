package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.request.AuthRequestDTO;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;

public interface AuthService {
    ApiResponse signIn(AuthRequestDTO authRequestDTO);
}
