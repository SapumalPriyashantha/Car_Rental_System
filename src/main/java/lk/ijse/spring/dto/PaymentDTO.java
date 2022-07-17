package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentDTO {
    private String pay_id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date pay_date;
    private int no_of_km;
    private BigDecimal rental_fee;
    private BigDecimal driver_fee;
    private BigDecimal lost_damage_slip_payment;
    private BigDecimal damage_cost;
    private BigDecimal refund;
    private BigDecimal total_payment;

    private ReservationDTO reservation;
}
