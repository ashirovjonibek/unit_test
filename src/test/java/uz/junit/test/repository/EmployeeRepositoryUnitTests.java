package uz.junit.test.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import uz.junit.test.entity.Employee;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryUnitTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Test 1:Save Employee Test")
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest() {

        Employee employee = Employee.builder()
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        employeeRepository.save(employee);

        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest() {

        Employee employee = employeeRepository.findById(1L).get();

        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest() {
        List<Employee> employees = employeeRepository.findAll();

        System.out.println(employees);
        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {
        Optional<Employee> employeeOptional = employeeRepository.findById(1L);
        Assertions.assertThat(employeeOptional).isPresent();

        Employee employee = employeeOptional.get();
        employee.setEmail("samcurran@gmail.com");
        Employee employeeUpdated = employeeRepository.save(employee);

        System.out.println(employeeUpdated);
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("samcurran@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {
        employeeRepository.deleteById(1L);
        Optional<Employee> employeeOptional = employeeRepository.findById(1L);
        Assertions.assertThat(employeeOptional).isEmpty();
    }

}
