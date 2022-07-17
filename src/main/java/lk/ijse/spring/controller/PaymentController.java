package lk.ijse.spring.controller;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.service.PaymentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil PaymentForReservation(@RequestBody PaymentDTO dto) {
        System.out.println(dto.toString());
        paymentService.PaymentForReservation(dto);
        return new ResponseUtil(200,"Payment For Reservation successfully Completed",null);
    }

    @GetMapping(path="generatePaymentId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generatePaymentId() {
        return new ResponseUtil(200,"ok",paymentService.generatePaymentId());
    }

    @GetMapping(path="dailyIncome",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil dailyIncome(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"ok",paymentService.dailyIncome(start_date,end_date));
    }

    @GetMapping(path="weeklyIncome",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil weeklyIncome(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"ok",paymentService.weeklyIncome(start_date,end_date));
    }

    @GetMapping(path="monthlyIncome",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil monthlyIncome(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"ok",paymentService.monthlyIncome(start_date,end_date));
    }

    @GetMapping(path="yearlyIncome",params={"start_date","end_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil yearlyIncome(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date) {
        return new ResponseUtil(200,"ok",paymentService.yearlyIncome(start_date,end_date));
    }
}
