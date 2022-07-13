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
        System.out.println(reservationDTO.toString());
        for (MultipartFile myFile: files) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, " Registration failed", null);
            }
        }

//        customerDTO.setLicense_img("uploads/"+customerDTO.getLicense_img());
//        customerDTO.setNic_img("uploads/"+customerDTO.getNic_img());

        reservationService.saveReservation(reservationDTO);

        return new ResponseUtil(200,"Registration Success",null);
    }

    @GetMapping(path = "/{nic}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchReservationByCustomerId(@PathVariable("nic") String id) {
        return new ResponseUtil(200,"Ok",reservationService.searchReservationByCustomerId(id));
    }
}
