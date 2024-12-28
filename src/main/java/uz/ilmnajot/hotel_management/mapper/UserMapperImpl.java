package uz.ilmnajot.hotel_management.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserDetailsRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.UserDetailsResponseDTO;
import uz.ilmnajot.hotel_management.dto.response.UserResponseDTO;
import uz.ilmnajot.hotel_management.dto.response.UserShiftResponseDTO;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserDetails;
import uz.ilmnajot.hotel_management.entity.UserShift;
import uz.ilmnajot.hotel_management.service.RoleService;
import uz.ilmnajot.hotel_management.service.UserDetailsService;
import uz.ilmnajot.hotel_management.service.UserService;

@Component
public class UserMapperImpl implements UserMapper {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final RoleService roleService;


    public UserMapperImpl(UserDetailsService userDetailsService, UserService userService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.roleService = roleService;
    }

    public UserDetails toUserDetails(UserDetailsRequestDTO userDetailsRequestDTO) {
        UserDetails details = new UserDetails();
        User user = this.userService.getUserById(userDetailsRequestDTO.getUserId());
        details.setUser(user);
        details.setDetailsStatus(userDetailsRequestDTO.getDetailsStatus());
        details.setPassportNumber(userDetailsRequestDTO.getPassportNumber());
        details.setExpirationDate(userDetailsRequestDTO.getExpirationDate());
        details.setDetails(userDetailsRequestDTO.getDetails());
        return details;
    }

    public UserDetailsResponseDTO toUserDetailsResponseDTO(UserDetails userDetails){
        UserDetailsResponseDTO responseDTO = new UserDetailsResponseDTO();
        responseDTO.setId(userDetails.getId());
        responseDTO.setDetailsStatus(userDetails.getDetailsStatus());
        responseDTO.setPassportNumber(userDetails.getPassportNumber());
        responseDTO.setExpirationDate(userDetails.getExpirationDate());
        responseDTO.setDetails(userDetails.getDetails());
        responseDTO.setUserId(userDetails.getUser().getId());
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

    public UserShiftResponseDTO toUserShiftResponseDTO(UserShift userShift){
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
        user.setPhone(userRequestDTO.getPhone());
        user.setUserDetails(toUserDetails(userRequestDTO.getUserDetailsRequestDTO()));
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
        responseDTO.setPhone(user.getPhone());
        responseDTO.setUserDetailsId(user.getUserDetails().getId());
        responseDTO.setUserShiftId(user.getUserShift().getId());
        responseDTO.setRoleId(user.getRole().getId());
        return responseDTO;
    }
}
