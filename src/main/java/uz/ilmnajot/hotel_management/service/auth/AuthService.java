package uz.ilmnajot.hotel_management.service.auth;

import uz.ilmnajot.hotel_management.dto.request.AuthRequestDTO;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;

public interface AuthService {
    ApiResponse signIn(AuthRequestDTO authRequestDTO);

    ApiResponse signUp(UserRequestDTO userRequestDTO);

    ApiResponse verifyEmail(String email, String emailCode);
}
