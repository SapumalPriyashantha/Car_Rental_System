package lk.ijse.spring.service;

import lk.ijse.spring.dto.AdminDTO;

public interface AdminService {
    void saveAdmin(AdminDTO dto);
    AdminDTO checkAdmin(String userName,String password);
    AdminDTO getAdmin (String userName,String password);
}
