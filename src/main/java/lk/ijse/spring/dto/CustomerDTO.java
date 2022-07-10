package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CustomerDTO {
    private String nic;
    private String user_name;
    private String password;
    private String customer_name;
    private String license_no;
    private String license_img;
    private String nic_img;
    private String address;
    private String con_number;
    private String email;
}
