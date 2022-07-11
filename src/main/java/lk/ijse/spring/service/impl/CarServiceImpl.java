package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveCar(CarDTO dto) {
        if (!repo.existsById(dto.getRegistration_no())) {
            System.out.println("working04");
            repo.save(mapper.map(dto, Car.class));
        } else {
            System.out.println("working05");
            throw new RuntimeException("Car Already Exist..!");
        }

    }
}
