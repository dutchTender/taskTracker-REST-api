package gov.nara.um.service.impl;

import gov.nara.common.persistence.service.AbstractLongIdService;
import gov.nara.um.persistence.dao.ITaskRewardsDAO;
import gov.nara.um.persistence.model.TaskReward;
import gov.nara.um.service.ITaskRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class TaskRewardService extends AbstractLongIdService<TaskReward> implements ITaskRewardService {

    @Autowired
    private ITaskRewardsDAO dao;
    TaskRewardService(){super();}

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
