package uz.junit.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.junit.test.Model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}