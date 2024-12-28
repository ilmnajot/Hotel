package uz.ilmnajot.hotel_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.AuthRequestDTO;
import uz.ilmnajot.hotel_management.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("sign-in")
    public HttpEntity<ApiResponse> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        ApiResponse apiResponse = authService.signIn(authRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }
}
