package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.ReservationDTO;

import java.util.List;

public interface DriverService {
    void saveDriver(DriverDTO dto);
    DriverDTO getDriverByID (String driverNIC);
    void updateDriver(DriverDTO dto);
    List<DriverDTO> getAllDrivers();
    DriverDTO getRandomDriver(String start_date,String end_date);
    List<Object>DriverScheduleByDate(String driver_nic,String start_date,String end_date);
    List<DriverDTO> todayAvailableDrivers();
    List<DriverDTO> todayUnavailableDrivers();
}
