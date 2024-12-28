package uz.ilmnajot.hotel_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.response.UserResponseDTO;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.mapper.UserMapper;
import uz.ilmnajot.hotel_management.repository.UserRepository;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

import java.lang.module.ResolutionException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


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


    public User getUserById(Long userId) {
        return userRepository.findByIdAndDeletedFalse(userId).orElseThrow(
                () -> new ResolutionException(ErrorMessage.USER_NOT_FOUND));
    }
}
