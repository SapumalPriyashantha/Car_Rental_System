package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.service.CustomerService;
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
@RequestMapping("api/v1/car")
@CrossOrigin
public class CarController {

    @Autowired
    CarService carService;

    String frontImage=null;
    String backImage=null;
    String rightImage=null;
    String leftImage=null;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCar(@RequestPart("file") MultipartFile[] files, @RequestPart("car") CarDTO carDTO) {

        for (MultipartFile myFile: files) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

                if( frontImage==null){
                    frontImage="uploads/"+myFile.getOriginalFilename();
                }else if (backImage==null){
                    backImage="uploads/"+myFile.getOriginalFilename();
                }else if (rightImage==null){
                    rightImage="uploads/"+myFile.getOriginalFilename();
                }else {
                    leftImage="uploads/"+myFile.getOriginalFilename();
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, " Registration failed", null);
            }
        }

        carDTO.getCarImgDetail().setFront(frontImage);
        carDTO.getCarImgDetail().setBack(backImage);
        carDTO.getCarImgDetail().setSide_01(rightImage);
        carDTO.getCarImgDetail().setSide_02(leftImage);

        System.out.println(frontImage);
        System.out.println(backImage);
        System.out.println(rightImage);
        System.out.println(leftImage);

        carService.saveCar(carDTO);

        return new ResponseUtil(200,"Registration Success",null);
    }

    @GetMapping(path = "{type}/{transmission}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCar(@PathVariable("type") String type,@PathVariable("transmission") String transmission) {
        return new ResponseUtil(200,"Ok",carService.searchCar(type,transmission));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCar() {
        return new ResponseUtil(200,"Ok",carService.getAllCar());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCar(@RequestBody CarDTO car) {
        carService.updateCar(car);
        return new ResponseUtil(200,"Updated Car",null);
    }

    @GetMapping(path="getAvailableCarsForCustomers",params={"pick_up_date","return_date","type","transmission"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil availableCarsForCustomers(@RequestParam("pick_up_date")String pickUp_date,@RequestParam("return_date")String return_date,@RequestParam("type")String type,@RequestParam("transmission")String transmission) {
        return new ResponseUtil(200,"Ok",carService.availableCarsForCustomers(pickUp_date,return_date,type,transmission));
    }

    @GetMapping(path="unavailableCarForAdmin",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil unavailableCarForAdmin(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"Ok",carService.unavailableCarForAdmin(start_date,end_date));
    }

    @PutMapping(path="updateStatusForCar",params={"registration_no","status"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateStatusForCar(@RequestParam("registration_no")String registration_no,@RequestParam("status")String status) {
        carService.updateStatusForCar(registration_no,status);
        return new ResponseUtil(200,"updated status for car",null);
    }
    @PutMapping(path="updateTotalKMForCar",params={"registration_no","KM"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTotalKMForCar(@RequestParam("registration_no")String registration_no,@RequestParam("KM")int KM) {
        carService.updateTotalKMForCar(registration_no,KM);
        return new ResponseUtil(200,"updated Total KM for car",null);
    }

    @GetMapping(path="maintenanceNeedCar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllMaintenanceNeedCar() {
        return new ResponseUtil(200,"Ok",carService.getAllMaintenanceNeedCar());
    }

    @GetMapping(path="getCar",params={"registration_no"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCar(@RequestParam("registration_no")String registration_no) {
        return new ResponseUtil(200,"Ok",carService.getCar(registration_no));
    }

}
