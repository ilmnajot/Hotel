package uz.ilmnajot.hotel_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.UserShiftRequestDTO;
import uz.ilmnajot.hotel_management.service.UserShiftService;

@RestController
@RequestMapping("/api/user-shift")
public class UserShiftController {

    private final UserShiftService userShiftService;

    public UserShiftController(UserShiftService userShiftService) {
        this.userShiftService = userShiftService;
    }

    @PostMapping("/{userId}/assign-shift")
    public HttpEntity<ApiResponse> assignShift(@PathVariable(name = "userId") Long userId, @RequestBody UserShiftRequestDTO userShiftRequestDTO) {
        ApiResponse apiResponse = userShiftService.assignShift(userId, userShiftRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }
}
