package uz.ilmnajot.hotel_management.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.dto.request.UserDetailsRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.UserDetailsResponseDTO;
import uz.ilmnajot.hotel_management.dto.response.UserResponseDTO;
import uz.ilmnajot.hotel_management.dto.response.UserShiftResponseDTO;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserDetail;
import uz.ilmnajot.hotel_management.entity.UserShift;
import uz.ilmnajot.hotel_management.exception.AlreadyExistsException;
import uz.ilmnajot.hotel_management.service.RoleService;
import uz.ilmnajot.hotel_management.service.UserService;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UserDetail toUserDetails(UserDetailsRequestDTO userDetailsRequestDTO) {
        if (userDetailsRequestDTO == null) {
            throw new AlreadyExistsException("User details must not be null");
        }
        UserDetail details = new UserDetail();
        User user = this.userService.getUserById(userDetailsRequestDTO.getUserId());
        details.setUser(user);
        details.setDetailsStatus(userDetailsRequestDTO.getDetailsStatus());
        details.setPassportNumber(userDetailsRequestDTO.getPassportNumber());
        details.setExpirationDate(userDetailsRequestDTO.getExpirationDate());
        details.setDetails(userDetailsRequestDTO.getDetails());
        return details;
    }

    public UserDetailsResponseDTO toUserDetailsResponseDTO(UserDetail userDetail) {
        UserDetailsResponseDTO responseDTO = new UserDetailsResponseDTO();
        responseDTO.setId(userDetail.getId());
        responseDTO.setDetailsStatus(userDetail.getDetailsStatus());
        responseDTO.setPassportNumber(userDetail.getPassportNumber());
        responseDTO.setExpirationDate(userDetail.getExpirationDate());
        responseDTO.setDetails(userDetail.getDetails());
        responseDTO.setUserId(userDetail.getUser().getId());
        return responseDTO;
    }

    public UserShift toUserShift(UserShiftRequestDTO userShiftRequestDTO) {
        UserShift shift = new UserShift();
        shift.setName(userShiftRequestDTO.getName());
        shift.setStartDate(userShiftRequestDTO.getStartDate());
        shift.setEndDate(userShiftRequestDTO.getEndDate());
        shift.setStartTime(userShiftRequestDTO.getStartTime());
        shift.setEndTime(userShiftRequestDTO.getEndTime());
        shift.setDescription(userShiftRequestDTO.getDescription());
        User user = this.userService.getUserById(userShiftRequestDTO.getUserId());
        shift.setUser(user);
        return shift;
    }

    public UserShiftResponseDTO toUserShiftResponseDTO(UserShift userShift) {
        UserShiftResponseDTO responseDTO = new UserShiftResponseDTO();
        responseDTO.setId(userShift.getId());
        responseDTO.setName(userShift.getName());
        responseDTO.setStartDate(userShift.getStartDate());
        responseDTO.setEndDate(userShift.getEndDate());
        responseDTO.setStartTime(userShift.getStartTime());
        responseDTO.setEndTime(userShift.getEndTime());
        responseDTO.setDescription(userShift.getDescription());
        responseDTO.setUserId(userShift.getUser().getId());
        return responseDTO;
    }

    public User toUserEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setFName(userRequestDTO.getFName());
        user.setLName(userRequestDTO.getLName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setPhone(userRequestDTO.getPhone());
        user.setUserDetail(toUserDetails(userRequestDTO.getUserDetailsRequestDTO()));
        user.setUserShift(toUserShift(userRequestDTO.getUserShiftRequestDTO()));
        user.setRole(this.roleService.getRoleById(userRequestDTO.getRoleId()));
        return user;

    }

    @Override
    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setFName(user.getFName());
        responseDTO.setLName(user.getLName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPassword(user.getPassword());
        responseDTO.setPhone(user.getPhone());
        responseDTO.setUserDetailsId(user.getUserDetail().getId());
        responseDTO.setUserShiftId(user.getUserShift().getId());
        responseDTO.setRoleId(user.getRole().getId());
        return responseDTO;
    }

    @Override
    public User toUpdateUser(User user, UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getFName() != null) {
            user.setFName(userRequestDTO.getFName());
        }
        if (userRequestDTO.getLName() != null) {
            user.setLName(userRequestDTO.getLName());
        }
        if (userRequestDTO.getEmail() != null) {
            user.setEmail(userRequestDTO.getEmail());
        }
        if (userRequestDTO.getPhone() != null) {
            user.setPhone(userRequestDTO.getPhone());
        }
        if (userRequestDTO.getUserDetailsRequestDTO() != null) {
            UserDetail userDetail = user.getUserDetail();
            if (userDetail == null) {
                userDetail = new UserDetail();
            }
            userDetail = toUserDetails(userRequestDTO.getUserDetailsRequestDTO());
            user.setUserDetail(userDetail);
        }
        if (userRequestDTO.getUserShiftRequestDTO() != null) {
            UserShift userShift = user.getUserShift();
            if (userShift == null) {
                userShift = new UserShift();
            }
            userShift = toUserShift(userRequestDTO.getUserShiftRequestDTO());
            user.setUserShift(userShift);
        }
        if (userRequestDTO.getRoleId() != null) {
            user.setRole(this.roleService.getRoleById(userRequestDTO.getRoleId()));
        }
        return user;
    }
}
