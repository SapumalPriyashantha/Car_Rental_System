package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    void saveDriver(DriverDTO dto);
    DriverDTO getDriverByID (String driverNIC);
    void updateDriver(DriverDTO dto);
    List<DriverDTO> getAllDrivers();
    DriverDTO getRandomDriver(String start_date,String end_date);
}
