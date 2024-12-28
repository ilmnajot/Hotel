package uz.ilmnajot.hotel_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.service.BookingsService;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping("book-room")
    public HttpEntity<ApiResponse> bookRoom(@RequestParam(name = "roomId") Long roomId,
                                            @RequestParam(name = "userId") Long userId){
        ApiResponse apiResponse = bookingsService.bookRoom(roomId, userId);
        return ResponseEntity.ok(apiResponse);
    }
}
