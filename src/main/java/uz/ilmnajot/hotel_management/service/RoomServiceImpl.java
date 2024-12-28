package uz.ilmnajot.hotel_management.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.RoomResponseDTO;
import uz.ilmnajot.hotel_management.entity.Room;
import uz.ilmnajot.hotel_management.exception.AlreadyExistsException;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.mapper.RoomMapper;
import uz.ilmnajot.hotel_management.repository.RoomRepository;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public Room findRoomById(int roomNumber) {
        return roomRepository.findByRoomNumberAndDeletedFalse(roomNumber).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.ROOM_NOT_FOUND));
    }

    @Override
    public Room findByRoomId(Long roomId) {
        return roomRepository.findByIdAndDeletedFalse(roomId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.ROOM_NOT_FOUND));
    }

    @Override
    public ApiResponse addRoom(RoomRequestDTO roomRequestDTO) {
        Optional<Room> optionalRoom = roomRepository.findByRoomNumberAndDeletedFalse(roomRequestDTO.getRoomNumber());
        if (optionalRoom.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.ROOM_ALREADY_EXIST);
        }
        Room roomEntity = roomMapper.toRoomEntity(roomRequestDTO);
        roomEntity = roomRepository.save(roomEntity);
        RoomResponseDTO roomResponseDTO = roomMapper.toRoomResponseDTO(roomEntity);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, roomResponseDTO);
    }
}
