package gov.nara.um.service.impl;

import gov.nara.common.persistence.service.AbstractLongIdService;
import gov.nara.um.persistence.dao.ITaskRewardsDAO;
import gov.nara.um.persistence.model.TaskReward;
import gov.nara.um.service.ITaskConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class TaskConfigurationService extends AbstractLongIdService<TaskReward> implements ITaskConfigurationService {

    @Autowired
    private ITaskRewardsDAO dao;
    TaskConfigurationService(){super();}

    @Override
    protected JpaSpecificationExecutor<TaskReward> getSpecificationExecutor() {
        return this.getSpecificationExecutor();
    }
    @Override
    public TaskReward findByName(String name) {
        return this.findByName(name);
    }
    @Override
    public TaskReward findOne(Long id) {
        return this.findOne(id);
    }
    @Override
    public void delete(Long id) {
         this.delete(id);
    }
    @Override
    protected ITaskRewardsDAO getDao() {
        return this.dao;
    }
}
