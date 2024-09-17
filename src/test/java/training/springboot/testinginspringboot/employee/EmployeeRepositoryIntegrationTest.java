package training.springboot.testinginspringboot.employee;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class EmployeeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * #### Fin de la configuration
     * @RunWith(SpringRunner.class) fournit un pont entre
     * les fonctionnalités de test Spring Boot et JUnit.
     * Chaque fois que nous utilisons des fonctionnalités de test Spring Boot dans nos tests JUnit
     * cette annotation sera requise.
     *
     * @DataJpaTest fournit une configuration standard nécessaire
     * pour tester la couche de persistance.
     *
     * Pour effectuer des opérations de base de données,
     * nous avons besoin de certains enregistrements déjà présents dans notre base de données.
     * Pour configurer ces données, nous pouvons utiliser TestEntityManager.
     *
     */

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // Given: On insere un employe dans la bd
        Employee alex = new Employee("Alex");
        entityManager.persist(alex);
        entityManager.flush();

        // When: Lire l'employe par son nom
        Employee found = employeeRepository.findByName(alex.getName());

        // Then
        assertThat(found).isEqualTo(alex.getName());
    }


}