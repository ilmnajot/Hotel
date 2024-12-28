package uz.ilmnajot.hotel_management.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.dto.request.BookingsRequestDTO;
import uz.ilmnajot.hotel_management.dto.response.BookingsResponseDTO;
import uz.ilmnajot.hotel_management.entity.Bookings;
import uz.ilmnajot.hotel_management.entity.Room;
import uz.ilmnajot.hotel_management.entity.User;

@Component
public class BookingsMapperImpl implements BookingsMapper {


    @Override
    public Bookings toBookingsEntity(BookingsRequestDTO bookingsRequestDTO) {
        return null;
    }

    @Override
    public BookingsResponseDTO toBookingResponseDTO(User user, Room room) {
        return null;
    }
}
