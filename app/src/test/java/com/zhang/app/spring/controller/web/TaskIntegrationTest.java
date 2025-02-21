package com.zhang.app.spring.controller.web;

import com.zhang.core.persistence.dao.ITaskDAO;
import com.zhang.core.persistence.dto.TaskDTOInterface;
import com.zhang.core.persistence.model.Task;
import com.zhang.app.spring.UmPersistenceJpaConfig;
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
public class TaskIntegrationTest {
// we are going to modify this to do a dataJPA test for integration testing


    @Autowired
    private ITaskDAO taskDAO;
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
        Task task1 = taskDAO.findByName(task.getName());
        //TaskDTOInterface task2 = businessUnitDao.findById(task1.getId()).orElseThrow();

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
        //task.setId(6L);
        task.setTaskDescription("xxxxxxxxx");
        task.setTaskTime("apex");
        entityManager.persist(task);
        entityManager.flush();

        //when
        TaskDTOInterface task1 = taskDAO.findByNameIgnoreCase(task.getName());
        System.out.println("################################################");
        System.out.println(task1);
        System.out.println("################################################");
        entityManager.remove(task);
        entityManager.flush();

        Task task2 = taskDAO.findByName(task.getName());

        // verify that we can find the entity that was just added
        assertThat(task2).isEqualTo(null);
    }





}
