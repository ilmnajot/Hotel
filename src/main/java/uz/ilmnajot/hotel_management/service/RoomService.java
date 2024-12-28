package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.entity.Room;

public interface RoomService {
    Room findRoomById(int roomNumber);
    Room findByRoomId(Long roomId);
    ApiResponse addRoom(RoomRequestDTO roomRequestDTO);
}
