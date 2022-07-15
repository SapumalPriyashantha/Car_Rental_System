package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.Reservation;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.repo.ReservationRepo;
import lk.ijse.spring.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveReservation(ReservationDTO dto) {
        if (!repo.existsById(dto.getReservation_id())) {
            repo.save(mapper.map(dto, Reservation.class));
        } else {
            throw new RuntimeException("Reservation Already Exist..!");
        }
    }

    @Override
    public List<ReservationDTO> searchReservationByCustomerId(String cust_nic) {
        return mapper.map(repo.searchReservationByCustomerId(cust_nic), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public void updateReservationStatus(String reservation_id, String reservation_status) {
        if (repo.existsById(reservation_id)) {
            Reservation reservation = repo.findById(reservation_id).get();
            reservation.setReservation_status(reservation_status);
            repo.save(reservation);
        } else {
            throw new RuntimeException("No Such Reservation To Update Status..! Please Check the ID..!");
        }
    }


}
