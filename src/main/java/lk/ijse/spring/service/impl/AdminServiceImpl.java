package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Admin;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.AdminRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.repo.ReservationRepo;
import lk.ijse.spring.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveAdmin(AdminDTO dto) {
        repo.save(mapper.map(dto, Admin.class));
    }

    @Override
    public AdminDTO checkAdmin(String userName, String password) {
        return mapper.map(repo.findByAdmin_nameAndPassword(userName, password), AdminDTO.class);
    }

    @Override
    public AdminDTO getAdmin(String userName, String password) {
        return mapper.map(repo.findByAdmin_nameAndPassword(userName, password), AdminDTO.class);
    }
}
