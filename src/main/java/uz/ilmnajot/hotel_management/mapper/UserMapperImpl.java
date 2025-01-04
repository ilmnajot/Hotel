package uz.ilmnajot.hotel_management.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.dto.request.UserDetailsRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.GuestResponseDTO;
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
        details.setDetailsStatus(userDetailsRequestDTO.getDetailsStatus());
        details.setDocumentNo(userDetailsRequestDTO.getPassportNumber());
        details.setExpirationDate(userDetailsRequestDTO.getExpirationDate());
        details.setDetails(userDetailsRequestDTO.getDetails());
        return details;
    }

    public UserDetailsResponseDTO toUserDetailsResponseDTO(UserDetail userDetail) {
        UserDetailsResponseDTO responseDTO = new UserDetailsResponseDTO();
        responseDTO.setId(userDetail.getId());
        responseDTO.setDetailsStatus(userDetail.getDetailsStatus());
        responseDTO.setPassportNumber(userDetail.getDocumentNo());
        responseDTO.setExpirationDate(userDetail.getExpirationDate());
        responseDTO.setDetails(userDetail.getDetails());
        return responseDTO;
    }

    public UserShift toUserShift(User user, UserShiftRequestDTO userShiftRequestDTO) {
        UserShift shift = new UserShift();
        shift.setUser(user);
        shift.setStartTime(userShiftRequestDTO.getStartTime());
        shift.setEndTime(userShiftRequestDTO.getEndTime());
        shift.setStartDate(userShiftRequestDTO.getStartDate());
        shift.setEndDate(userShiftRequestDTO.getEndDate());
        shift.setDescription(userShiftRequestDTO.getDescription());
        return shift;
    }

    public UserShiftResponseDTO toUserShiftResponseDTO(User user, UserShift userShift) {
        UserShiftResponseDTO responseDTO = new UserShiftResponseDTO();
        responseDTO.setId(userShift.getId());
        responseDTO.setUserId(user.getId());
        responseDTO.setStartTime(userShift.getStartTime());
        responseDTO.setEndTime(userShift.getEndTime());
        responseDTO.setStartDate(userShift.getStartDate());
        responseDTO.setEndDate(userShift.getEndDate());
        responseDTO.setDescription(userShift.getDescription());
        return responseDTO;
    }

    public User toUserEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setFName(userRequestDTO.getFName());
        user.setLName(userRequestDTO.getLName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setPhone(userRequestDTO.getPhone());
        user.setUserDetail(this.toUserDetails(userRequestDTO.getUserDetailsRequestDTO()));
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
        responseDTO.setRoleId(user.getRole().getId());
        return responseDTO;
    }


    @Override
    public GuestResponseDTO toGuestResponseDTO(User user) {
        GuestResponseDTO responseDTO = new GuestResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setFName(user.getFName());
        responseDTO.setLName(user.getLName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPassword(user.getPassword());
        responseDTO.setPhone(user.getPhone());
        responseDTO.setUserDetailsId(user.getUserDetail().getId());
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
//        if (userRequestDTO.getUserShiftRequestDTO() != null) {
//            UserShift userShift = user.getUserShift();
//            if (userShift == null) {
//                userShift = new UserShift();
//            }
//            userShift = toUserShift(userRequestDTO.getUserShiftRequestDTO());
//            user.setUserShift(userShift);
//        }
        if (userRequestDTO.getRoleId() != null) {
            user.setRole(this.roleService.getRoleById(userRequestDTO.getRoleId()));
        }
        return user;
    }
}
