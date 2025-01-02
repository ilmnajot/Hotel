package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserShift;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.repository.UserRepository;
import uz.ilmnajot.hotel_management.repository.UserShiftRepository;
import uz.ilmnajot.hotel_management.service.UserShiftService;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;

@Service
public class UserShiftServiceImpl implements UserShiftService {

    private final UserShiftRepository userShiftRepository;
    private final UserRepository userRepository;

    public UserShiftServiceImpl(UserShiftRepository userShiftRepository, UserRepository userRepository) {
        this.userShiftRepository = userShiftRepository;
        this.userRepository = userRepository;
    }

    public UserShift getUserShift(Long userId) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND));
        return user.getUserShift();
    }

}
