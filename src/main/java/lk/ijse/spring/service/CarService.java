package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);
    List<CarDTO> searchCar(String type,String transmission);
}
