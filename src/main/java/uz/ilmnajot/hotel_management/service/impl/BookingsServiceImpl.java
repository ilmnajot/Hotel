package uz.ilmnajot.hotel_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.dto.common.ApiResponse;
import uz.ilmnajot.hotel_management.entity.Bookings;
import uz.ilmnajot.hotel_management.entity.Room;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.enums.BookingStatus;
import uz.ilmnajot.hotel_management.enums.Status;
import uz.ilmnajot.hotel_management.mapper.BookingsMapper;
import uz.ilmnajot.hotel_management.repository.BookingsRepository;
import uz.ilmnajot.hotel_management.repository.RoomRepository;
import uz.ilmnajot.hotel_management.repository.UserRepository;
import uz.ilmnajot.hotel_management.service.BookingsService;
import uz.ilmnajot.hotel_management.service.RoomService;
import uz.ilmnajot.hotel_management.service.UserService;
import uz.ilmnajot.hotel_management.utils.SuccessMessage;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;
    private final RoomService roomService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingsMapper bookingsMapper;


    @Override
    public ApiResponse bookRoom(Long roomId, Long userId) {
        User user = userService.getUserById(userId);
        Room room = roomService.findByRoomId(roomId);
        if (room.getStatus() == Status.BOOKED) {
            return new ApiResponse("failed", false, HttpStatus.BAD_REQUEST, "Book has been booked already");
        }

        Bookings bookings = new Bookings();
        bookings.setCheckInDate(LocalDate.now());
        bookings.setCheckOutDate(LocalDate.now().plusDays(1));
        bookings.setBookingStatus(BookingStatus.CONFIRMED);
        bookings.setUser(user);
        bookings.setRoom(room);

        bookingsRepository.save(bookings);

        room.setStatus(Status.BOOKED);
        roomRepository.save(room);
        return new ApiResponse(SuccessMessage.SUCCESS, true, HttpStatus.OK, "Room has been booked successfully");
    }
}
