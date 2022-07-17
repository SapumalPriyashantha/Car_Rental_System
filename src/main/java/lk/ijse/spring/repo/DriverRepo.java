package lk.ijse.spring.repo;


import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, String> {
    @Query(value = "SELECT * FROM driver WHERE nic NOT IN (SELECT DISTINCT driver_nic FROM car_reservation WHERE  (pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2 )) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Driver getRandomDriver (String start_date,String end_date);

    @Query(value = "SELECT DISTINCT * FROM car_reservation WHERE (pick_up_date BETWEEN ?2 AND ?3 OR return_date BETWEEN ?2 AND ?3 ) AND driver_nic=?1", nativeQuery = true)
    List<Object> DriverScheduleByDate(String driver_nic,String start_date,String end_date);

}
