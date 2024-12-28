package uz.ilmnajot.hotel_management.mapper;

import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.RoomResponseDTO;
import uz.ilmnajot.hotel_management.entity.Room;

public interface RoomMapper {
    Room toRoomEntity(RoomRequestDTO roomRequestDTO);
    RoomResponseDTO toRoomResponseDTO(Room room);
}
