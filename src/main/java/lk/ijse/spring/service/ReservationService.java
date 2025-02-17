package lk.ijse.spring.service;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    void saveReservation(ReservationDTO dto);
    List<ReservationDTO> searchReservationByCustomerId(String cust_nic);
    void updateReservationStatus(String reservation_id, String reservation_status,String status_reason);
    List<ReservationDTO> getAcceptReservation(String nic,String accept_status);
    List<ReservationDTO> getDenyReservation(String nic,String deny_status);
    List<ReservationDTO> getPendingReservation(String nic,String pending_status);
    String getGenerateOrderId();
    List<ReservationDTO> todayReservation(String date);
    List<ReservationDTO> todayPickup(String date);
    List<ReservationDTO> getAllPendingReservation();
    List<ReservationDTO> getAllAcceptReservation();
    List<ReservationDTO> DriverSchedule (String driver_nic,String start_date,String end_date);
}
