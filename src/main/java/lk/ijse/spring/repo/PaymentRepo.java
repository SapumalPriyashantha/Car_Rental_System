package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, String> {
    @Query(value = "select pay_id from reservation_payment ORDER BY pay_id DESC LIMIT 1", nativeQuery = true)
    String getGenerateOrderId();

    @Query(value = "SELECT * FROM reservation_payment WHERE pay_date BETWEEN ?1 AND ?2 ", nativeQuery = true)
    List<Payment> getIncome(String start_date, String end_date);
}
