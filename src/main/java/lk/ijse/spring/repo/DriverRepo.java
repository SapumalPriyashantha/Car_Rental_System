package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, String> {
    @Query(value = "SELECT * FROM driver WHERE nic NOT IN (SELECT DISTINCT driver_nic FROM car_reservation WHERE  (pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2 )) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Driver getRandomDriver (String start_date,String end_date);

    @Query(value = "SELECT DISTINCT * FROM car_reservation WHERE (pick_up_date BETWEEN ?2 AND ?3 OR return_date BETWEEN ?2 AND ?3 ) AND driver_nic=?1", nativeQuery = true)
    List<Object> DriverScheduleByDate(String driver_nic,String start_date,String end_date);

    @Query(value = "SELECT * FROM driver WHERE nic NOT IN (SELECT DISTINCT driver_nic FROM car_reservation WHERE  (current_date() BETWEEN pick_up_date AND return_date OR pick_up_date=current_date() OR return_date=current_date())) ", nativeQuery = true)
    List<Driver> todayAvailableDrivers ();

    @Query(value = "SELECT * FROM driver WHERE nic IN (SELECT DISTINCT driver_nic FROM car_reservation WHERE  (current_date() BETWEEN pick_up_date AND return_date OR pick_up_date=current_date() OR return_date=current_date())) ", nativeQuery = true)
    List<Driver> todayUnavailableDrivers ();

    @Query(value = "SELECT * FROM driver WHERE driver_name=?1 and license_no=?2", nativeQuery = true)
    Driver findByDriver_namedAndLicense_no(String driver_name,String license_no);

}
