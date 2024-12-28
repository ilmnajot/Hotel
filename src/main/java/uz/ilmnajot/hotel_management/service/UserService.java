package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.entity.User;

public interface UserService {

    User getUserById(Long userId);

    ApiResponse addEmployee(UserRequestDTO userRequestDTO);
}
