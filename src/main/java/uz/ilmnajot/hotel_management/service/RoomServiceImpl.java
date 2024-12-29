package uz.ilmnajot.hotel_management.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.dto.request.RoomRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.RoomBookingDetailsDTO;
import uz.ilmnajot.hotel_management.dto.response.RoomResponseDTO;
import uz.ilmnajot.hotel_management.entity.Room;
import uz.ilmnajot.hotel_management.exception.AlreadyExistsException;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.mapper.RoomMapper;
import uz.ilmnajot.hotel_management.repository.RoomRepository;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
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

    @Override
    public ApiResponse getRoom(Long roomId) {
        Room room = findByRoomId(roomId);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, room);
    }

    @Override
    public ApiResponse getAllRooms() {
        List<Room> roomList = roomRepository.findAllByDeletedIsFalse();
        List<RoomResponseDTO> responseDTOList = roomList.stream().map(roomMapper::toRoomResponseDTO).toList();
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, responseDTOList);
    }

    @Override
    public ApiResponse updateRoom(Long roomId, RoomRequestDTO roomRequestDTO) {
        Room room = findByRoomId(roomId);
        room = roomMapper.toUpdateRoomEntity(roomRequestDTO, room);
        room = roomRepository.save(room);
        RoomResponseDTO roomResponseDTO = roomMapper.toRoomResponseDTO(room);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, roomResponseDTO);
    }

    @Override
    public ApiResponse deleteRoom(Long roomId) {
        Room room = findByRoomId(roomId);
        room.setDeleted(true);
        room = roomRepository.save(room);
        RoomResponseDTO roomResponseDTO = roomMapper.toRoomResponseDTO(room);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, "Room has been deleted: " + roomResponseDTO);
    }

    @Override
    public ApiResponse getUnBookedRooms() {
        List<Room> roomList = roomRepository.findAllUnBookedRooms();
        if (roomList.isEmpty()) {
            return new ApiResponse(ErrorMessage.ROOM_NOT_FOUND, true, HttpStatus.OK, roomList);
        }
        List<RoomResponseDTO> responseDTOList = roomList
                .stream()
                .map(roomMapper::toRoomResponseDTO)
                .toList();
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, responseDTOList);
    }

    @Override
    public ApiResponse getBookedRooms() {
        List<Room> allBookedRooms = roomRepository.findAllBookedRooms();
        if (allBookedRooms.isEmpty()) {
            return new ApiResponse(ErrorMessage.ROOM_NOT_FOUND, true, HttpStatus.OK, allBookedRooms);
        }
        List<RoomResponseDTO> responseDTOList = allBookedRooms
                .stream()
                .map(roomMapper::toRoomResponseDTO)
                .toList();
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, responseDTOList);
    }

    @Override
    public ApiResponse getRoomsDatesRange(LocalDate startDate, LocalDate endDate) {
        List<RoomBookingDetailsDTO> detailsDTOList = roomRepository.findRoomsBookingsBetweenDates(startDate, endDate);
        if (detailsDTOList.isEmpty()) {
            return new ApiResponse(ErrorMessage.ROOM_NOT_FOUND, true, HttpStatus.OK, "No Details found: " + detailsDTOList);
        }
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, detailsDTOList);
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
}
