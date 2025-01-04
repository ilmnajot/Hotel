package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.UserShiftResponseDTO;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserShift;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.mapper.UserMapper;
import uz.ilmnajot.hotel_management.repository.UserShiftRepository;
import uz.ilmnajot.hotel_management.service.UserService;
import uz.ilmnajot.hotel_management.service.UserShiftService;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

@Service
public class UserShiftServiceImpl implements UserShiftService {

    private final UserShiftRepository userShiftRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserShiftServiceImpl(UserShiftRepository userShiftRepository, UserService userService, UserMapper userMapper) {
        this.userShiftRepository = userShiftRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Override
    public ApiResponse assignShift(Long userId, UserShiftRequestDTO userShiftRequestDTO) {
        User user = userService.getUserById(userId);
        UserShift userShift = userMapper.toUserShift(user, userShiftRequestDTO);
        UserShift savedUserShift = userShiftRepository.save(userShift);
        UserShiftResponseDTO userShiftResponseDTO = userMapper.toUserShiftResponseDTO(user, savedUserShift);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.CREATED, userShiftResponseDTO);
    }

    @Override
    public UserShift getUserShiftId(Long userShiftId) {
        return userShiftRepository.findById(userShiftId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.USER_SHIFT_NOT_FOUND));
    }

    @Override
    public UserShift getUserShiftByUserId(Long userId) {
        return userShiftRepository.findByUserIdAndDeletedFalse(userId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.USER_SHIFT_NOT_FOUND));
    }
}
