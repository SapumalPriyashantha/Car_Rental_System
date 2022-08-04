package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "car")
public class Car {
    @Id
    private String registration_no;
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private int no_of_passengers;
    private int km;
    private String fuel_type;
    private int daily_rate;
    private int monthly_rate;
    private int free_km_for_day;
    private int free_km_for_month;
    private BigDecimal price_for_extra_km;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private CarImgDetail carImgDetail;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
