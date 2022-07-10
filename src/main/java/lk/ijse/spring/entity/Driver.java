package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "driver")
public class Driver {
    @Id
    private String nic;
    private String driver_name;
    private String license_no;
    private String con_number;
    private String status;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DriverSchedule>driverSchedules;
}
