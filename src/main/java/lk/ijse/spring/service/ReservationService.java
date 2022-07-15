package lk.ijse.spring.service;

import lk.ijse.spring.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    void saveReservation(ReservationDTO dto);
    List<ReservationDTO> searchReservationByCustomerId(String cust_nic);
    void updateReservationStatus(String reservation_id, String reservation_status);
}
