package lk.ijse.spring.controller;


import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    DriverService driverService;

    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveDriver(@ModelAttribute DriverDTO driverDTO) {
       driverDTO.setStatus("Available");
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

    @GetMapping(path="AvailableDrivers",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil AvailableDrivers(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"Ok",driverService.AvailableDrivers(start_date,end_date));
    }

    @GetMapping(path="todayUnavailableDrivers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayUnavailableDrivers() {
        return new ResponseUtil(200,"Ok",driverService.todayUnavailableDrivers());
    }

    @PutMapping(path="changeDriverInReservation",params={"reservation_id","driver_nic"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil changeDriverInReservation(@RequestParam("reservation_id")String reservation_id,@RequestParam("driver_nic")String driver_nic) {
        driverService.changeDriverInReservation(reservation_id,driver_nic);
        return new ResponseUtil(200,"Change Driver",null);
    }

    @GetMapping(path="getDriver",params={"userName","password"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriver(@RequestParam("userName")String userName,@RequestParam("password")String password) {
        return new ResponseUtil(200,"Driver username and password correct",driverService.getDriver(userName,password));
    }
}
