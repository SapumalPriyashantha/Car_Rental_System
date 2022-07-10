package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "reservation_payment")
public class Payment {
    @Id
    private String pay_id;
    private Date pay_date;
    private int no_of_km;
    private BigDecimal rental_fee;
    private BigDecimal driver_fee;
    private BigDecimal lost_damage_slip_payment;
    private BigDecimal damage_cost;
    private BigDecimal refund;
    private BigDecimal total_payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Reservation Reservation;
}
