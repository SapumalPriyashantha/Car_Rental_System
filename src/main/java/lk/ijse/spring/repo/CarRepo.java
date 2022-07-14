package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, String> {
    @Query(value = "select * from car where type=?1 and transmission=?2", nativeQuery = true)
    List<Car> searchCar(String type,String transmission);

    //    @Query(value = "select * from Customer where name=?1 and address=?2", nativeQuery = true)
//    Customer searchCustomerFromName(String name, String address);
}
