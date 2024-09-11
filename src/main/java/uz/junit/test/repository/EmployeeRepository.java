package uz.junit.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.junit.test.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}