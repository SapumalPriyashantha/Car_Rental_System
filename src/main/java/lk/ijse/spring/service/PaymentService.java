package lk.ijse.spring.service;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.entity.Car;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentService {
    void PaymentForReservation(PaymentDTO dto);
    String generatePaymentId();
    List<PaymentDTO> dailyIncome(String start_date,String end_date);
    List<PaymentDTO> weeklyIncome(String start_date,String end_date);
    List<PaymentDTO> monthlyIncome(String start_date,String end_date);
    List<PaymentDTO> yearlyIncome(String start_date,String end_date);
}
