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

    @Override
    public List<CarDTO> availableCarsForCustomers(String pick_date, String return_date, String type, String transmission) {
        return mapper.map(repo.availableCarsForCustomers(pick_date,return_date,type,transmission), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> unavailableCarForAdmin(String start_date, String end_date) {
        return mapper.map(repo.unavailableCarForAdmin(start_date,end_date), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public void updateStatusForCar(String registration_no, String status) {
        if (repo.existsById(registration_no)) {
            Car car = repo.findById(registration_no).get();
            car.setStatus(status);
            repo.save(car);
        } else {
            throw new RuntimeException("No Such Car To Update Status..! Please Check the ID..!");
        }
    }

}
