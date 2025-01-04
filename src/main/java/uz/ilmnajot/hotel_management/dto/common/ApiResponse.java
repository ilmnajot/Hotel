package uz.ilmnajot.hotel_management.dto.common;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;
    private Object info;

    public ApiResponse(String message, boolean success, HttpStatus status, Object info) {
        this.message = message;
        this.success = success;
        this.status = status;
        this.info = info;
    }


    public ApiResponse(String message, boolean success, HttpStatus httpStatus) {
        this.message = message;
        this.success = success;
        this.status = httpStatus;
    }
}
