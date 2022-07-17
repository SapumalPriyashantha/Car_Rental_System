package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Payment;
import lk.ijse.spring.entity.Reservation;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.PaymentRepo;
import lk.ijse.spring.repo.ReservationRepo;
import lk.ijse.spring.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private PaymentRepo repo;

    @Autowired
    private ReservationRepo re_repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void PaymentForReservation(PaymentDTO dto) {
        if (!repo.existsById(dto.getPay_id())) {
            Reservation reservation = re_repo.findById(dto.getReservation().getReservation_id()).get();
            ReservationDTO reservationDTO = mapper.map(reservation, ReservationDTO.class);
            dto.setReservation(reservationDTO);
            repo.save(mapper.map(dto, Payment.class));
        } else {
            throw new RuntimeException("Payment already done..!");
        }
    }
}
