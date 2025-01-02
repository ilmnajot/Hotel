package uz.ilmnajot.hotel_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.hotel_management.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByIdAndDeletedFalse(Long roleId);
    Optional<Role> findByNameAndDeletedFalse(String roleName);
}
