package lk.ijse.spring.service;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO dto);
    void updateCustomer(CustomerDTO dto);
    CustomerDTO searchCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> registeredCustomerByDate();
    CustomerDTO checkCustomer(String userName, String password);
    CustomerDTO getCustomer(String userName, String password);
}
