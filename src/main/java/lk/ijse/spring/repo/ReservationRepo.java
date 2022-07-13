package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, String> {
//    @Modifying
//    @Query(value = "select c from Customer c where c.id=?1")
    @Query(value = "select * from car_reservation where customer.nic=?1 ", nativeQuery = true)
    List<Reservation> searchReservationByCustomerId(String id);

    List<Reservation> findAllByCustomer(Customer customer);


}
