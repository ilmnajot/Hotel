package uz.ilmnajot.hotel_management.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.AuthRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.AuthTokenResponse;
import uz.ilmnajot.hotel_management.dto.response.UserResponseDTO;
import uz.ilmnajot.hotel_management.entity.Role;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.enums.RoleType;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.jwt.JwtProvider;
import uz.ilmnajot.hotel_management.mapper.UserMapper;
import uz.ilmnajot.hotel_management.repository.UserRepository;
import uz.ilmnajot.hotel_management.service.RoleService;
import uz.ilmnajot.hotel_management.service.UserService;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;


    @Override
    public ApiResponse signIn(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword()
        ));
        User user = (User) authentication;
        User existsUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND));
        AuthTokenResponse response = new AuthTokenResponse();
        response.setToken(jwtProvider.generateAccessToken(existsUser));
        response.setUser(existsUser);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, response);
    }

    @Override
    public ApiResponse signUp(UserRequestDTO userRequestDTO) {
        User user = userService.getUserByEmail(userRequestDTO.getEmail());
        if (user.getRole().getRoleType().equals(RoleType.IN_REGISTRATION)) {
            userRepository.delete(user);
        }
        User userEntity = userMapper.toUserEntity(userRequestDTO);
        User savedUser = userRepository.save(userEntity);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(savedUser);
        // sending email
        return new ApiResponse("success", true, HttpStatus.CREATED, userResponseDTO);
    }

    @Override
    public ApiResponse verifyEmail(String email, String emailCode) {
        User user = userService.getUserByEmail(email);
        Role role = roleService.getRoleByName(RoleType.USER.name());
        if (user.getEmailCode() != null && user.getEmailCode().equals(emailCode)) {

            Timestamp now = new Timestamp(System.currentTimeMillis());
            Timestamp expireTime = new Timestamp(user.getCreatedAt().getTime() + (15 * 60 * 1000));

            if (now.after(expireTime)) {
                return new ApiResponse("Verification code has expired. Please request a new one", true, HttpStatus.BAD_REQUEST, "null");
            }
            user.setEnabled(true);
            user.setRole(role);
            user.setEmailCode(null);
            userRepository.save(user);

            return new ApiResponse("User has been verified successfully", true, HttpStatus.OK, user);
        } else {
            return new ApiResponse("Invalid verification code. Please try again.", false, HttpStatus.BAD_REQUEST, null);
        }
    }
}
