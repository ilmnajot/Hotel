package uz.ilmnajot.hotel_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add-room")
    public HttpEntity<ApiResponse> addRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
        ApiResponse apiResponse = roomService.addRoom(roomRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }
}
