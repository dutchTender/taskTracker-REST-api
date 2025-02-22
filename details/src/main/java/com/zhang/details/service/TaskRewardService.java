package com.zhang.details.service;

import com.zhang.common.base.service.AbstractLongIdService;
import com.zhang.core.persistence.dao.ITaskRewardsDAO;
import com.zhang.core.persistence.model.TaskReward;
import com.zhang.core.service.ITaskRewardService;
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
        return  dao.findByName(name);
    }
    @Override
    public TaskReward findOne(Long id) {
        return dao.findById(id).get();
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
