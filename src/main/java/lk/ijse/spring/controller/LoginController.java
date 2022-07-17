package lk.ijse.spring.controller;


import lk.ijse.spring.service.CarService;
import lk.ijse.spring.service.LoginService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/checkLogin")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(path="login",params={"user_name","pass_word"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil Login (@RequestParam("user_name")String user_name, @RequestParam("pass_word")String pass_word) {
        return new ResponseUtil(200,"Ok",loginService.checkUser_nameAndPass_word(user_name,pass_word));
    }
}
