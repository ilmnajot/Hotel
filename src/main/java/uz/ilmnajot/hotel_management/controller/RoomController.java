package uz.ilmnajot.hotel_management.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.service.RoomService;

import java.time.LocalDate;

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

    @GetMapping("/get-room/{roomId}")
    public HttpEntity<ApiResponse> getRoom(@PathVariable(name = "roomId") Long roomId) {
        ApiResponse apiResponse = roomService.getRoom(roomId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get-rooms")
    public HttpEntity<ApiResponse> getAllRooms() {
        ApiResponse apiResponse = roomService.getAllRooms();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get-unBooked-rooms")
    public HttpEntity<ApiResponse> getUnBookedRooms() {
        ApiResponse unBookedRooms = roomService.getUnBookedRooms();
        return ResponseEntity.ok(unBookedRooms);
    }

    @GetMapping("/get-booked-rooms")
    public HttpEntity<ApiResponse> getBookedRooms() {
        ApiResponse bookedRooms = roomService.getBookedRooms();
        return ResponseEntity.ok(bookedRooms);
    }
    @GetMapping("/get-rooms-dates")
    public HttpEntity<ApiResponse> getRoomsDatesRange( @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        ApiResponse roomsDatesRange = roomService.getRoomsDatesRange(startDate, endDate);
        return ResponseEntity.ok(roomsDatesRange);
    }

    @PutMapping("/update-room/{roomId}")
    public HttpEntity<ApiResponse> updateRoom(@PathVariable Long roomId, @RequestBody RoomRequestDTO roomRequestDTO) {
        ApiResponse apiResponse = roomService.updateRoom(roomId, roomRequestDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete-room/{roomId}")
    public HttpEntity<ApiResponse> deleteRoom(@PathVariable Long roomId) {
        ApiResponse apiResponse = roomService.deleteRoom(roomId);
        return ResponseEntity.ok(apiResponse);
    }
}
