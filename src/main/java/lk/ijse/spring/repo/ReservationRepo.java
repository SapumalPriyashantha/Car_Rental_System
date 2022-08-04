package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

    @Query(value = "select * from car_reservation where reservation_status='Pending'", nativeQuery = true)
    List<Reservation> getAllPendingReservation();

    @Query(value = "select * from car_reservation where reservation_status='Accept'", nativeQuery = true)
    List<Reservation> getAllAcceptReservation();

    @Query(value = "SELECT DISTINCT * FROM car_reservation WHERE (pick_up_date BETWEEN ?2 AND ?3 OR return_date BETWEEN ?2 AND ?3 ) AND driver_nic=?1 AND reservation_status='Accept'", nativeQuery = true)
    List<Reservation> DriverSchedule(String driver_nic,String start_date,String end_date);

}
