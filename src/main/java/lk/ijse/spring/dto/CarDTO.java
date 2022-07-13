package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CarDTO {
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

    private CarImgDetailsDTO carImgDetail;
}
