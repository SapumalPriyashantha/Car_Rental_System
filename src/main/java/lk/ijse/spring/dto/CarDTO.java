package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int no_of_passengers;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int km;

    private String fuel_type;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int daily_rate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int monthly_rate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int free_km_for_day;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int free_km_for_month;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private BigDecimal price_for_extra_km;

    private String status;

    private CarImgDetailsDTO carImgDetail;
}
