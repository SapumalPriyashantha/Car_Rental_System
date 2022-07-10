package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ReservationDTO {
    private String reservation_id;
    private Date reservation_date;
    private Date pick_up_date;
    private Date return_date;
    private Time pick_up_time;
    private BigDecimal waiver_payment;
    private String bank_slip_img;
    private String reservation_status;
    private String driver_status;
    private String reason;
}
