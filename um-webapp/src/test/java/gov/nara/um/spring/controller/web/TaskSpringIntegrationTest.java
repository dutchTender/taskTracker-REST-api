package gov.nara.um.spring.controller.web;

import gov.nara.um.persistence.dao.ITaskDAO;
import gov.nara.um.persistence.model.Task;
import gov.nara.um.spring.UmPersistenceJpaConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {UmPersistenceJpaConfig.class})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TaskSpringIntegrationTest {
// we are going to modify this to do a dataJPA test for integration testing


    @Autowired
    private ITaskDAO businessUnitDao;
    @Autowired
    private TestEntityManager entityManager;



    // tests jpa add and jpa find one
    @Test
    public final void whenFindByName_thenReturnBusinessUnit() {
        //given
        Task task = new Task();
        task.setName("xxxx");
        task.setTaskTime("xxxxxxxxx");
        task.setTaskDescription("apex");
        entityManager.persist(task);
        entityManager.flush();

        //when
        Task task1 = businessUnitDao.findByName(task.getName());

        // verify that we can find the entity that was just added
        assertThat(task1.getName()).isEqualTo(task.getName());
    }



    // test jpa delete
    // tests jpa add and jpa find one
    @Test
    public final void whenFindByName_thenReturnBusinessUnit_then_verifyDelete() {
        //given
        Task task = new Task();
        task.setName("xxxx");
        task.setTaskDescription("xxxxxxxxx");
        task.setTaskTime("apex");
        entityManager.persist(task);
        entityManager.flush();

        //when
        Task task1 = businessUnitDao.findByName(task.getName());
        entityManager.remove(task1);
        entityManager.flush();

        Task task2 = businessUnitDao.findByName(task.getName());

        // verify that we can find the entity that was just added
        assertThat(task2).isEqualTo(null);
    }





}
