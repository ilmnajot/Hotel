package uz.ilmnajot.hotel_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @PostMapping("/add-employee")
    public HttpEntity<ApiResponse> addEmployee(@RequestBody UserRequestDTO userRequestDTO) {
        ApiResponse apiResponse = userService.addEmployee(userRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEmployee{employeeId}")
    public HttpEntity<ApiResponse> getEmployee(@PathVariable Long employeeId) {
        ApiResponse apiResponse = userService.getEmployee(employeeId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get-all-employees")
    public HttpEntity<ApiResponse> getAllEmployees() {
        ApiResponse apiResponse = userService.getAllEmployees();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update-employee/{employeeId}")
    public HttpEntity<ApiResponse> updateEmployee(@PathVariable Long employeeId,
                                                  @RequestBody UserRequestDTO userRequestDTO) {
        ApiResponse apiResponse = userService.updateEmployee(employeeId, userRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete-employee/{employeeId}")
    public HttpEntity<ApiResponse> deleteEmployee(@PathVariable Long employeeId) {
        ApiResponse apiResponse = userService.deleteEmployee(employeeId);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getHistory")
    public HttpEntity<ApiResponse> getHistory(@RequestParam Long guestId) {
        ApiResponse apiResponse = userService.getHistory(guestId);
        return ResponseEntity.ok(apiResponse);
    }
}
