package lk.ijse.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "car_reservation")
public class Reservation {
    @Id
    private String reservation_id;
    private Date reservation_date;
    private Date pick_up_date;
    private Date return_date;
    private String pick_up_time;
    private BigDecimal waiver_payment;
    private String bank_slip_img;
    private String reservation_status;
    private String driver_status;
    private String reason; 

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn
    private Car car;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn
    private Driver driver;

}
