package uz.junit.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import uz.junit.test.Repository.EmployeeRepositoryUnitTests;
import uz.junit.test.controller.EmployeeControllerUnitTests;
import uz.junit.test.service.EmployeeServiceUnitTests;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmployeeControllerUnitTests.class,
        EmployeeRepositoryUnitTests.class,
        EmployeeServiceUnitTests.class
})
class TestApplicationTests {

    @Test
    void contextLoads() {
    }

}
