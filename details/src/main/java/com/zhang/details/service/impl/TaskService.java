package com.zhang.details.service.impl;
import com.zhang.common.persistence.service.AbstractLongIdService;
import com.zhang.core.persistence.dao.ITaskDAO;
import com.zhang.core.persistence.dao.IUserDAO;
import com.zhang.core.persistence.model.Task;
import com.zhang.core.persistence.model.User;
import com.zhang.core.service.ITaskService;
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
    public Task findTaskReference(final Long Id) {
             return dao.getReferenceById(Id);
    }


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
