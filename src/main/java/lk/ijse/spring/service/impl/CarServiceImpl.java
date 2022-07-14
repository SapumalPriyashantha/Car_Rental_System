package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            repo.save(mapper.map(dto, Car.class));
        } else {
            throw new RuntimeException("Car Already Exist..!");
        }

    }

    @Override
    public List<CarDTO> searchCar(String type, String transmission) {
        return mapper.map(repo.searchCar(type,transmission), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> getAllCar() {
        return mapper.map(repo.findAll(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public void updateCar(CarDTO dto) {
        if (repo.existsById(dto.getRegistration_no())) {
            repo.save(mapper.map(dto, Car.class));
        } else {
            throw new RuntimeException("No Such Car To Update..! Please Check the ID..!");
        }
    }
}
