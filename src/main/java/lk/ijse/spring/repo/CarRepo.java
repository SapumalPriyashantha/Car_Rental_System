package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, String> {
    @Query(value = "select * from car where type=?1 and transmission=?2", nativeQuery = true)
    List<Car> searchCar(String type,String transmission);

    @Query(value = "SELECT * FROM car WHERE registration_no NOT IN (SELECT DISTINCT reservation_id FROM car_reservation WHERE  (pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2 )) AND status='Available' AND type=?3 AND transmission=?4", nativeQuery = true)
    List<Car> availableCarsForCustomers(String pick_date, String return_date,String type,String transmission);

    @Query(value = "SELECT * FROM car WHERE registration_no IN (SELECT DISTINCT reservation_id FROM car_reservation WHERE  (pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2 )) AND status='Available' ", nativeQuery = true)
    List<Car> unavailableCarForAdmin(String start_date, String end_date);

//    @Query(value = "select * from car where type=?1 and transmission=?2", nativeQuery = true)
//    List<Car> searchCar(String type,String transmission);



    //    @Query(value = "select * from Customer where name=?1 and address=?2", nativeQuery = true)
//    Customer searchCustomerFromName(String name, String address);
}
