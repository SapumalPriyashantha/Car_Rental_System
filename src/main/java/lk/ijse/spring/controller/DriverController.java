package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    DriverService driverService;

    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveDriver(@ModelAttribute DriverDTO driverDTO) {
        driverService.saveDriver(driverDTO);
        return new ResponseUtil(200,"save Driver",null);
    }

    @GetMapping(path="getDriverByNIC",params={"driverNIC"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriverByID(@RequestParam("driverNIC")String driverNIC) {
        return new ResponseUtil(200,"Ok",driverService.getDriverByID(driverNIC));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateDriver(@RequestBody DriverDTO dto) {
        driverService.updateDriver(dto);
        return new ResponseUtil(200,"Updated Driver..!",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllDrivers() {
        return new ResponseUtil(200,"Ok",driverService.getAllDrivers());
    }

    @GetMapping(path="getRandomDriver",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getRandomDriver(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"Ok",driverService.getRandomDriver(start_date,end_date));
    }

    @GetMapping(path="DriverScheduleByDate",params={"driver_nic","start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil DriverScheduleByDate(@RequestParam("driver_nic")String driver_nic,@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"Ok",driverService.DriverScheduleByDate(driver_nic,start_date,end_date));
    }

    @GetMapping(path="todayAvailableDrivers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayAvailableDrivers() {
        return new ResponseUtil(200,"Ok",driverService.todayAvailableDrivers());
    }

    @GetMapping(path="todayUnavailableDrivers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayUnavailableDrivers() {
        return new ResponseUtil(200,"Ok",driverService.todayUnavailableDrivers());
    }

    @PutMapping(path="changeDriverInReservation",params={"reservation_id","driver_nic"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil changeDriverInReservation(@RequestParam("reservation_id")String reservation_id,@RequestParam("driver_nic")String driver_nic) {
        return new ResponseUtil(200,"Ok",driverService.changeDriverInReservation(reservation_id,driver_nic));
    }
}
