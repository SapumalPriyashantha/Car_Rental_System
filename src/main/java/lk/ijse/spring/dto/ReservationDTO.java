package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ReservationDTO {
    private String reservation_id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date reservation_date;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date pick_up_date;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date return_date;
    @JsonFormat(pattern="HH:mm:ss")
    private Time pick_up_time;
    private BigDecimal waiver_payment;
    private String bank_slip_img;
    private String reservation_status;
    private String driver_status;
    private String reason;

    private CustomerDTO customer;

    private CarDTO car;

    private DriverDTO driver;
}
