package uz.ilmnajot.hotel_management.controller.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.AuthRequestDTO;
import uz.ilmnajot.hotel_management.dto.request.UserRequestDTO;
import uz.ilmnajot.hotel_management.service.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public HttpEntity<ApiResponse> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        ApiResponse apiResponse = authService.signIn(authRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/sign-up")
    public HttpEntity<ApiResponse> signUp(@RequestBody UserRequestDTO userRequestDTO){
        authService.signUp(userRequestDTO);
        return null;
    }

    @PostMapping("/verify")
    public HttpEntity<ApiResponse> verifyAuthentication(@RequestParam String email, @RequestParam String emailCode){
        ApiResponse apiResponse = authService.verifyEmail(email, emailCode);
        return ResponseEntity.ok(apiResponse);
    }
}
