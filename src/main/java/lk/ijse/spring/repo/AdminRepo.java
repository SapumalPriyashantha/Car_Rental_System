package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin, String> {
    @Query(value = "SELECT * FROM admin WHERE admin_name=?1 and password=?2", nativeQuery = true)
    Admin findByAdmin_nameAndPassword(String admin_name,String pass_word);
}
