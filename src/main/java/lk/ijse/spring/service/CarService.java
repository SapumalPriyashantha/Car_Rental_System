package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);
    List<CarDTO> searchCar(String type,String transmission);
    List<CarDTO> getAllCar();
    void updateCar(CarDTO dto);
}
