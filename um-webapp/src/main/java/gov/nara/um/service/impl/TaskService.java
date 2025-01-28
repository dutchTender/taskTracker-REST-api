package gov.nara.um.service.impl;
import gov.nara.common.persistence.service.AbstractLongIdService;
import gov.nara.um.persistence.dao.ITaskDAO;
import gov.nara.um.persistence.dao.IUserDAO;
import gov.nara.um.persistence.model.Task;
import gov.nara.um.persistence.model.User;
import gov.nara.um.service.ITaskService;
import gov.nara.um.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class TaskService extends AbstractLongIdService<Task> implements ITaskService {

    @Autowired
    private ITaskDAO dao;
    @Autowired
    private IUserDAO userDao;
    public TaskService() {
        super();
    }
    // API
    // find
    @Override
    @Transactional(readOnly = true)
    public Task findByName(final String name) {
        return dao.findByName(name);
    }
    // other
    // remove user
    // Spring
    @Override
    protected final ITaskDAO getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Task> getSpecificationExecutor() {
        return dao;
    }
    @Override
    public Task addUser(String unitId, String userId) {
        Optional <Task> taskOptional =  dao.findById(Long.valueOf(unitId));
        Task task =  taskOptional.get();
        Optional <User> userOptional = userDao.findById(Long.valueOf(userId));
        User user = userOptional.get();
        if(task != null && user != null){
            dao.save(task);
            userDao.save(user);
        }
        return task;
    }
    @Override
    public Task removerUser(String unitId, String userId) {
        Optional <Task> taskOptional =  dao.findById(Long.valueOf(unitId));
        Task task = taskOptional.get();
        Optional <User> userOptional = userDao.findById(Long.valueOf(userId));
        User user = userOptional.get();
        if(task != null && user != null){
            dao.save(task);
            userDao.save(user);
        }
        return task;
    }
}
