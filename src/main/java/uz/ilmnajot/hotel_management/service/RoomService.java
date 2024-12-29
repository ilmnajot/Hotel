package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.entity.Room;

import java.time.LocalDate;

public interface RoomService {
    Room findRoomById(int roomNumber);
    Room findByRoomId(Long roomId);
    ApiResponse addRoom(RoomRequestDTO roomRequestDTO);

    ApiResponse getRoom(Long roomId);

    ApiResponse getAllRooms();

    ApiResponse updateRoom(Long roomId, RoomRequestDTO roomRequestDTO);

    ApiResponse deleteRoom(Long roomId);

    ApiResponse getUnBookedRooms();

    ApiResponse getBookedRooms();

    ApiResponse getRoomsDatesRange(LocalDate startDate, LocalDate endDate);
}
