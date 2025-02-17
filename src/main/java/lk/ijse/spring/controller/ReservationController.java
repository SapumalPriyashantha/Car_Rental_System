package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.service.ReservationService;
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
@RequestMapping("api/v1/reservation")
@CrossOrigin
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveReservation(@RequestPart("file") MultipartFile[] files, @RequestPart("reservation") ReservationDTO reservationDTO) {

        for (MultipartFile myFile: files) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));
                reservationDTO.setBank_slip_img("uploads/"+myFile.getOriginalFilename());

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, " Registration failed", null);
            }
        }

        reservationService.saveReservation(reservationDTO);

        return new ResponseUtil(200,"Reservation Success",null);
    }

    @GetMapping(path = "/{nic}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchReservationByCustomerId(@PathVariable("nic") String id) {
        return new ResponseUtil(200,"Ok",reservationService.searchReservationByCustomerId(id));
    }

    @PutMapping(path="updateReservationStatus",params={"reservation_id","reservation_status","status_reason"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateReservationStatus(@RequestParam("reservation_id")String reservation_id,@RequestParam("reservation_status")String reservation_status,@RequestParam("status_reason")String status_reason) {
        reservationService.updateReservationStatus(reservation_id,reservation_status,status_reason);
        return new ResponseUtil(200,"Updated Reservation Status",null);
    }

    @GetMapping(path="acceptReservation",params={"nic","accept_status"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAcceptReservation(@RequestParam("nic")String nic,@RequestParam("accept_status")String accept_status) {
        return new ResponseUtil(200,"ok",reservationService.getAcceptReservation(nic,accept_status));
    }

    @GetMapping(path="denyReservation",params={"nic","deny_status"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDenyReservation(@RequestParam("nic")String nic,@RequestParam("deny_status")String deny_status) {
        return new ResponseUtil(200,"ok",reservationService.getDenyReservation(nic,deny_status));
    }

    @GetMapping(path="pendingReservation",params={"nic","pending_status"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getPendingReservation(@RequestParam("nic")String nic,@RequestParam("pending_status")String pending_status) {
        return new ResponseUtil(200,"ok",reservationService.getPendingReservation(nic,pending_status));
    }

    @GetMapping(path="generateReservationId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateOrderId() {
        return new ResponseUtil(200,"ok",reservationService.getGenerateOrderId());
    }

    @GetMapping(path="todayReservation",params={"date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayReservation(@RequestParam("date")String date) {
        return new ResponseUtil(200,"ok",reservationService.todayReservation(date));
    }

    @GetMapping(path="todayPickup",params={"date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayPickup(@RequestParam("date")String date) {
        return new ResponseUtil(200,"ok",reservationService.todayPickup(date));
    }

    @GetMapping(path="getAllPendingReservation",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllPendingReservation() {
        return new ResponseUtil(200,"ok",reservationService.getAllPendingReservation());
    }

    @GetMapping(path="getAllAcceptReservation",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllAcceptReservation() {
        return new ResponseUtil(200,"ok",reservationService.getAllAcceptReservation());
    }

    @GetMapping(path="DriverSchedule",params={"driver_nic","start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil DriverSchedule(@RequestParam("driver_nic")String driver_nic,@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"Ok",reservationService.DriverSchedule(driver_nic,start_date,end_date));
    }
}
