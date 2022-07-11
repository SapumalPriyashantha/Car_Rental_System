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

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil getAllCustomers() {
//         return new ResponseUtil(200,"Ok",customerService.getAllCustomers());
//    }

//    @ResponseStatus(HttpStatus.CREATED) //201
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil saveCustomer(@ModelAttribute CustomerDTO customer, @RequestPart MultipartFile file) {
//        customerService.saveCustomer(customer);
//        return new ResponseUtil(200,"Save",null);
//    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCustomer(@RequestPart("file") MultipartFile[] files,@RequestPart("customer") CustomerDTO customerDTO) {

        for (MultipartFile myFile: files) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                return new ResponseUtil(500, " Registration failed", null);
            }
        }

        customerDTO.setLicense_img("uploads/"+customerDTO.getLicense_img());
        customerDTO.setNic_img("uploads/"+customerDTO.getNic_img());

        customerService.saveCustomer(customerDTO);

        return new ResponseUtil(200,"Registration Success",null);
    }


//    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customer) {
//        customerService.updateCustomer(customer);
//        return new ResponseUtil(200,"Updated",null);
//    }
//
//    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil deleteCustomer(@RequestParam String id) {
//        customerService.deleteCustomer(id);
//        return new ResponseUtil(200,"Deleted",null);
//    }
//
//    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil searchCustomer(@PathVariable String id) {
//        return new ResponseUtil(200,"Ok",customerService.searchCustomer(id));
//    }


}
