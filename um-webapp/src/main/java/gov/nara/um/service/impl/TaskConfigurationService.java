package gov.nara.um.service.impl;

import gov.nara.common.persistence.service.AbstractLongIdService;
import gov.nara.um.persistence.dao.ITaskConfigurationDAO;
import gov.nara.um.persistence.model.TaskConfiguration;
import gov.nara.um.service.ITaskConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class TaskConfigurationService extends AbstractLongIdService<TaskConfiguration> implements ITaskConfigurationService {

    @Autowired
    private ITaskConfigurationDAO dao;
    TaskConfigurationService(){super();}

    @Override
    protected JpaSpecificationExecutor<TaskConfiguration> getSpecificationExecutor() {
        return this.getSpecificationExecutor();
    }
    @Override
    public TaskConfiguration findByName(String name) {
        return this.findByName(name);
    }
    @Override
    public TaskConfiguration findOne(Long id) {
        return this.findOne(id);
    }
    @Override
    public void delete(Long id) {
         this.delete(id);
    }
    @Override
    protected ITaskConfigurationDAO getDao() {
        return this.dao;
    }
}
