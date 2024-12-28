package uz.ilmnajot.hotel_management.mapper;

import uz.ilmnajot.hotel_management.dto.request.BookingsRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.BookingsResponseDTO;
import uz.ilmnajot.hotel_management.entity.Bookings;
import uz.ilmnajot.hotel_management.entity.Room;
import uz.ilmnajot.hotel_management.entity.User;

public interface BookingsMapper {

    Bookings toBookingsEntity(BookingsRequestDTO bookingsRequestDTO);
    BookingsResponseDTO toBookingResponseDTO(User user, Room room);
}
