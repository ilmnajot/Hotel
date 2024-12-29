package uz.ilmnajot.hotel_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.hotel_management.dto.response.RoomBookingDetailsDTO;
import uz.ilmnajot.hotel_management.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumberAndDeletedFalse(int roomNumber);
    Optional<Room> findByIdAndDeletedFalse(Long roomId);
    List<Room> findAllByDeletedIsFalse();

    @Query("select r from rooms as r where r.status='AVAILABLE'")
    List<Room> findAllUnBookedRooms();

    @Query("select r from rooms as r where r.status='BOOKED'")
    List<Room> findAllBookedRooms();

    @Query("select new uz.ilmnajot.hotel_management.dto.response.RoomBookingDetailsDTO(r.roomNumber, r.floor, r.roomType,r.price, r.status, r.features, " +
            "b.user.fName, b.user.lName, b.user.email, b.checkInDate, b.checkOutDate) from rooms as r LEFT join bookings as b on r.id =b.room.id " +
            "where (b.checkInDate<= :startDate and b.checkOutDate>= :endDate) or b.id is null")
    List<RoomBookingDetailsDTO> findRoomsBookingsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
