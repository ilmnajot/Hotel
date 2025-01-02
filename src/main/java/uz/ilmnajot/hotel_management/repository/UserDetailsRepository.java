package uz.ilmnajot.hotel_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.hotel_management.entity.UserDetail;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findByIdAndDeletedFalse(Long id);
}
