package uz.ilmnajot.hotel_management.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.RoomResponseDTO;
import uz.ilmnajot.hotel_management.entity.Room;

@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room toRoomEntity(RoomRequestDTO roomRequestDTO) {
        Room room = new Room();
        room.setRoomNumber(roomRequestDTO.getRoomNumber());
        room.setFloor(roomRequestDTO.getFloor());
        room.setRoomType(roomRequestDTO.getRoomType());
        room.setPrice(roomRequestDTO.getPrice());
        room.setStatus(roomRequestDTO.getStatus());
        room.setFeatures(roomRequestDTO.getFeatures());
        return room;
    }

    @Override
    public RoomResponseDTO toRoomResponseDTO(Room room) {
        RoomResponseDTO responseDTO = new RoomResponseDTO();
        responseDTO.setId(room.getId());
        responseDTO.setRoomNumber(room.getRoomNumber());
        responseDTO.setFloor(room.getFloor());
        responseDTO.setRoomType(room.getRoomType());
        responseDTO.setPrice(room.getPrice());
        responseDTO.setStatus(room.getStatus());
        responseDTO.setFeatures(room.getFeatures());
        return responseDTO;
    }

    @Override
    public Room toUpdateRoomEntity(RoomRequestDTO roomRequestDTO, Room room) {
        if (roomRequestDTO.getRoomNumber()!=0){
            room.setRoomNumber(roomRequestDTO.getRoomNumber());
        }
        if (roomRequestDTO.getFloor()!=0){
            room.setFloor(roomRequestDTO.getFloor());
        }
        if (roomRequestDTO.getRoomType()!=null){
            room.setRoomType(roomRequestDTO.getRoomType());
        }
        if (roomRequestDTO.getPrice()!=null){
            room.setPrice(roomRequestDTO.getPrice());
        }
        if (roomRequestDTO.getStatus()!=null){
            room.setStatus(roomRequestDTO.getStatus());
        }
        if (roomRequestDTO.getFeatures()!=null){
            room.setFeatures(roomRequestDTO.getFeatures());
        }
        return room;
    }
}
