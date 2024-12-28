package uz.ilmnajot.hotel_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.hotel_management.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
