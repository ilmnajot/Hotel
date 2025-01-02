package uz.ilmnajot.hotel_management.mapper;

import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserDetailsRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.UserResponseDTO;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserDetail;
import uz.ilmnajot.hotel_management.entity.UserShift;

public interface UserMapper {
    User toUserEntity(UserRequestDTO userRequestDTO);
    UserShift toUserShift(UserShiftRequestDTO userShiftRequestDTO);
    UserDetail toUserDetails(UserDetailsRequestDTO userDetailsRequestDTO);
    UserResponseDTO toUserResponseDTO(User user);
    User toUpdateUser(User user, UserRequestDTO userRequestDTO);
}
