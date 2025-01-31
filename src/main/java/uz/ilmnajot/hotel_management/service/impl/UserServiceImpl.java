package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.response.GuestResponseDTO;
import uz.ilmnajot.hotel_management.dto.response.UserResponseDTO;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.mapper.UserMapper;
import uz.ilmnajot.hotel_management.repository.UserRepository;
import uz.ilmnajot.hotel_management.service.UserService;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, @Lazy UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public ApiResponse addEmployee(UserRequestDTO userRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmailAndDeletedFalse(userRequestDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new ResourceNotFoundException(ErrorMessage.USER_ALREADY_EXISTS);
        }
        User userEntity = userMapper.toUserEntity(userRequestDTO);
        userEntity = userRepository.save(userEntity);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(userEntity);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, userResponseDTO);
    }

    @Override
    public ApiResponse getEmployee(Long employeeId) {
        User user = getUserById(employeeId);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, userResponseDTO);
    }

    @Override
    public ApiResponse getAllEmployees() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> responseDTOList = userList
                .stream()
                .map(userMapper::toUserResponseDTO)
                .toList();
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, responseDTOList);
    }

    @Override
    public ApiResponse updateEmployee(Long employeeId, UserRequestDTO userRequestDTO) {
        User user = getUserById(employeeId);
        user = userMapper.toUpdateUser(user, userRequestDTO);
        user = userRepository.save(user);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, userResponseDTO);
    }

    @Override
    public ApiResponse deleteEmployee(Long employeeId) {
        return null;
    }

    @Override
    public ApiResponse getHistory(Long guestId) {
        User user = userRepository.findById(guestId).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND));
        GuestResponseDTO userResponseDTO = userMapper.toGuestResponseDTO(user);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, userResponseDTO);
    }

    public User getUserById(Long userId) {
        return userRepository.findByIdAndDeletedFalse(userId).orElseThrow(
                () -> new ResolutionException(ErrorMessage.USER_NOT_FOUND));
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new ResolutionException(ErrorMessage.USER_NOT_FOUND));
    }
}
