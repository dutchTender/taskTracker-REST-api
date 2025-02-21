package com.zhang.details.service;
import com.zhang.core.persistence.dao.ITaskDAO;
import com.zhang.core.persistence.dto.TaskDTO;
import com.zhang.core.persistence.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)

public class TaskServiceUnitTest {

    @Test
    public void testAddTask() {
        ITaskDAO mockDAO = Mockito.mock(ITaskDAO.class);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskTime("next day");
        taskDTO.setTaskDescription("test description");
        taskDTO.setName("123xxx");
        Mockito.when(mockDAO.findByName(Mockito.anyString())).thenReturn(new Task(taskDTO));
        assertEquals(mockDAO.findByName(taskDTO.getName()).getName(), taskDTO.getName());
    }
}
