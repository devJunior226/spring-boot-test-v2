package training.springboot.testinginspringboot.employee;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceImplIntegrationTest {

    /**
     * @TestConfiguration verifie la classe Service en creant un @Bean du service
     * afin de pouvoir etre @Autowire dans la classe de test.
     */
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    /**
     * @MockBean crée un Mock pour EmployeeRepository ,
     * qui peut être utilisé pour contourner l'appel au EmployeeRepository réel.
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        Employee alex = new Employee("Alex");
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    }

    /** Fin de la configuration. Passe aux cas de tests */
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "Alex";
        Employee employeeFound = employeeService.getEmployeeByName(name);

        assertThat(employeeFound.getName()).isEqualTo(name);
    }
}




















