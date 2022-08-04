package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.entity.Reservation;
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
    public void updateReservationStatus(String reservation_id, String reservation_status,String status_reason) {
        if (repo.existsById(reservation_id)) {
            Reservation reservation = repo.findById(reservation_id).get();
            reservation.setReservation_status(reservation_status);
            reservation.setReason(status_reason);
            repo.save(reservation);
        } else {
            throw new RuntimeException("No Such Reservation To Update Status..! Please Check the ID..!");
        }
    }

    @Override
    public List<ReservationDTO> getAcceptReservation(String nic, String accept_status) {
        return mapper.map(repo.getReservations(nic,accept_status), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getDenyReservation(String nic, String deny_status) {
        return mapper.map(repo.getReservations(nic,deny_status), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getPendingReservation(String nic, String pending_status) {
        return mapper.map(repo.getReservations(nic,pending_status), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public String getGenerateOrderId() {
        String RE_Id = repo.getGenerateOrderId();
        System.out.println(RE_Id);

        if (RE_Id != null){
            int tempId = Integer.
                    parseInt(RE_Id.split("RE-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "RE-00"+tempId;
            }else if(tempId<99){
                return "RE-0"+tempId;
            }else{
                return "RE-"+tempId;
            }

        }else{
            return "RE-001";
        }
    }

    @Override
    public List<ReservationDTO> todayReservation(String date) {
        return mapper.map(repo.getReservationsByReservation_date(date), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> todayPickup(String date) {
        return mapper.map(repo.getReservationsByPick_up_date(date), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getAllPendingReservation() {
        return mapper.map(repo.getAllPendingReservation(), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getAllAcceptReservation() {
        return mapper.map(repo.getAllAcceptReservation(), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> DriverSchedule(String driver_nic, String start_date, String end_date) {
        return mapper.map(repo.DriverSchedule(driver_nic,start_date,end_date), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

}
