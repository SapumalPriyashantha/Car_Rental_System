package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);
    List<CarDTO> searchCar(String type,String transmission);
    List<CarDTO> getAllCar();
    void updateCar(CarDTO dto);
    List<CarDTO> availableCarsForCustomers(String pick_date,String return_date,String type,String transmission);
    List<CarDTO> unavailableCarForAdmin(String start_date,String end_date);
    void updateStatusForCar(String registration_no,String status);
    void updateTotalKMForCar(String registration_no,int KM);
}
