package com.zhang.details.service;
import com.zhang.common.base.service.AbstractLongIdService;
import com.zhang.core.persistence.dao.ITaskRewardsDAO;
import com.zhang.core.persistence.model.TaskReward;
import com.zhang.core.service.ITaskRewardService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class TaskRewardService extends AbstractLongIdService<TaskReward> implements ITaskRewardService {
    private final ITaskRewardsDAO dao;
    TaskRewardService(ITaskRewardsDAO dao){
        super();
        this.dao = dao;
    }
    @Override
    protected JpaSpecificationExecutor<TaskReward> getSpecificationExecutor() {
        return this.getSpecificationExecutor();
    }
    @Override
    public TaskReward findByName(String name) {
        return  dao.findByName(name);
    }
    @Override
    public TaskReward findOne(Long id) {
        return dao.findById(id).get();
    }
    @Override
    protected ITaskRewardsDAO getDao() {
        return this.dao;
    }
}
