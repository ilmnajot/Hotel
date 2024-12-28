package uz.ilmnajot.hotel_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.hotel_management.service.UserShiftService;

@RestController
@RequestMapping("/api/user-shift")
public class UserShiftController {

    private final UserShiftService userShiftService;

    public UserShiftController(UserShiftService userShiftService) {
        this.userShiftService = userShiftService;
    }
}
