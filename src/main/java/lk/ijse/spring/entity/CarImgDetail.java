package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "car_img_detail")
public class CarImgDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int img_id;
    private String front;
    private String back;
    private String side_01;
    private String side_02;
}
