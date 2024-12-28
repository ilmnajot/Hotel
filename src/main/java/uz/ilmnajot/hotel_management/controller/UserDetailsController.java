package uz.ilmnajot.hotel_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.hotel_management.service.UserDetailsService;

@RestController
@RequestMapping("/api/user-details")
public class UserDetailsController {
    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
