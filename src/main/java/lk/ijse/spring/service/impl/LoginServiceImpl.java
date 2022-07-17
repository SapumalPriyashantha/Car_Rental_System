package lk.ijse.spring.service.impl;

import lk.ijse.spring.entity.Admin;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.*;
import lk.ijse.spring.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomerRepo customer_repo;

    @Autowired
    private DriverRepo driver_repo;

    @Autowired
    private AdminRepo admin_repo;

    @Override
    public String checkUser_nameAndPass_word(String user_name, String pass_word) {
        System.out.println("working");
        Customer customer = customer_repo.findCustomerByUser_nameAndPassword(user_name, pass_word);
        Driver driver = driver_repo.findByDriver_namedAndLicense_no(user_name, pass_word);
        Admin admin = admin_repo.findByAdmin_nameAndPassword(user_name, pass_word);

        if(customer_repo.findCustomerByUser_nameAndPassword(user_name, pass_word) != null){
            return "That was a Customer";
        }else if (driver_repo.findByDriver_namedAndLicense_no(user_name, pass_word) != null){
            return "That was a Driver";
        }else if (admin_repo.findByAdmin_nameAndPassword(user_name, pass_word) != null){
            return "That was a Admin";
        }else {
           return "Incorrect User name OR pass word";
        }
    }
}
