package com.zhang.details.service;
import com.zhang.common.base.service.AbstractLongIdService;
import com.zhang.core.persistence.dao.ITaskDAO;
import com.zhang.core.persistence.dao.IUserDAO;
import com.zhang.core.persistence.model.Task;
import com.zhang.core.service.ITaskService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class TaskService extends AbstractLongIdService<Task> implements ITaskService {


    private final ITaskDAO dao;
    private final IUserDAO userDao;

    public TaskService(ITaskDAO dao, IUserDAO userDao) {
        super();
        this.dao = dao;
        this.userDao = userDao;
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
}
