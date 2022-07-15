package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, String> {

    @Query(value = "select * from car_reservation where customer_nic=?1 ", nativeQuery = true)
    List<Reservation> searchReservationByCustomerId(String id);

    @Query(value = "select * from car_reservation where customer_nic=?1 and reservation_status=?2", nativeQuery = true)
    List<Reservation> getReservations(String nic,String status);

    @Query(value = "select reservation_id from car_reservation ORDER BY reservation_id DESC LIMIT 1", nativeQuery = true)
    String getGenerateOrderId();

    @Query(value = "select * from car_reservation where reservation_date=?1", nativeQuery = true)
    List<Reservation> getReservationsByReservation_date(String date);

    @Query(value = "select * from car_reservation where pick_up_date=?1", nativeQuery = true)
    List<Reservation> getReservationsByPick_up_date(String date);

}
