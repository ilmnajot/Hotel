package uz.ilmnajot.hotel_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.hotel_management.entity.Room;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumberAndDeletedFalse(int roomNumber);
    Optional<Room> findByIdAndDeletedFalse(Long roomId);
}
