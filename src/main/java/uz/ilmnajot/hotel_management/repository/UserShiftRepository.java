package uz.ilmnajot.hotel_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.hotel_management.entity.UserShift;

import java.util.Optional;

@Repository
public interface UserShiftRepository extends JpaRepository<UserShift, Long> {

    Optional<UserShift> findByUserIdAndDeletedFalse(Long userId);
}
