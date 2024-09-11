package uz.junit.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.junit.test.entity.Employee;
import uz.junit.test.repository.EmployeeRepository;
import uz.junit.test.service.Impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceUnitTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;


    @BeforeEach
    public void setup(){

        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();

    }

    @Test
    @Order(1)
    public void saveEmployeeTest(){
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        System.out.println(savedEmployee);
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @Order(2)
    public void getEmployeeById(){
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        Employee existingEmployee = employeeService.getEmployeeById(employee.getId()).get();

        System.out.println(existingEmployee);
        assertThat(existingEmployee).isNotNull();

    }


    @Test
    @Order(3)
    public void getAllEmployee(){
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        List<Employee> employeeList = employeeService.getAllEmployees();

        System.out.println(employeeList);
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void updateEmployee(){

        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        employee.setEmail("max@gmail.com");
        employee.setFirstName("Max");
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee updatedEmployee = employeeService.updateEmployee(employee,employee.getId());

        System.out.println(updatedEmployee);
        assertThat(updatedEmployee.getEmail()).isEqualTo("max@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Max");
    }

    @Test
    @Order(5)
    public void deleteEmployee(){

        willDoNothing().given(employeeRepository).deleteById(employee.getId());

        employeeService.deleteEmployee(employee.getId());

        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }


}
