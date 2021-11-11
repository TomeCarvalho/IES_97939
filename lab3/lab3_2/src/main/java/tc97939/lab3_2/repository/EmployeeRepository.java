package tc97939.lab3_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tc97939.lab3_2.model.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    Employee findByEmailId(String email);
    List<Employee> findEmployeesByEmailId(String emailId);
}
