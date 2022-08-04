package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    String nic_image=null;
    String license_image=null;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCustomers() {
         return new ResponseUtil(200,"Ok",customerService.getAllCustomers());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCustomer(@RequestPart("customer") CustomerDTO customerDTO,@RequestPart("file") MultipartFile[] files) {

        for (MultipartFile myFile: files) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

                if (license_image==null){
                    license_image="uploads/"+myFile.getOriginalFilename();
                }else {
                    nic_image="uploads/"+myFile.getOriginalFilename();
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, " Registration failed", null);
            }
        }

        customerDTO.setLicense_img(license_image);
        customerDTO.setNic_img(nic_image);

        customerService.saveCustomer(customerDTO);

        return new ResponseUtil(200,"Registration Success",null);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customer) {
        customerService.updateCustomer(customer);
        return new ResponseUtil(200,"Updated Customer",null);
    }

    @GetMapping(path = "/{nic}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCustomer(@PathVariable("nic") String id) {
        return new ResponseUtil(200,"Ok",customerService.searchCustomer(id));
    }

    @GetMapping(path = "toDayRegisterCustomer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayRegisteredCustomers() {
        return new ResponseUtil(200,"Ok",customerService.registeredCustomerByDate());
    }

    @GetMapping(path="getCustomer",params={"userName","password"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriver(@RequestParam("userName")String userName,@RequestParam("password")String password) {
        return new ResponseUtil(200,"Customer username and password correct",customerService.getCustomer(userName,password));
    }

}
