package lk.ijse.spring.controller;


import lk.ijse.spring.service.*;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/checkLogin")
@CrossOrigin
public class LoginController {

//    @Autowired
//    LoginService loginService;

    @Autowired
    CustomerService customerService;

    @Autowired
    DriverService driverService;

    @Autowired
    AdminService adminService;

    @GetMapping(path="login",params={"user_name","pass_word"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil Login (@RequestParam("user_name")String user_name, @RequestParam("pass_word")String pass_word) {

        if(customerService.checkCustomer(user_name,pass_word) != null){
            return new ResponseUtil(200,"Ok",customerService.checkCustomer(user_name,pass_word));
        }else if (driverService.checkDriver(user_name,pass_word) != null){
            return new ResponseUtil(200,"Ok",driverService.checkDriver(user_name,pass_word));
        }else if (adminService.checkAdmin(user_name,pass_word) != null){
            return new ResponseUtil(200,"Ok",adminService.checkAdmin(user_name,pass_word));
        }else {
            return new ResponseUtil(200,"Ok","Invalid username or password");
        }
    }
}
