package com.zhang.um.persistence.dao;

import com.zhang.common.interfaces.IByNameApi;
import com.zhang.um.persistence.model.TaskReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskRewardsDAO extends JpaRepository<TaskReward, Long>, JpaSpecificationExecutor<TaskReward>, IByNameApi<TaskReward> {
    public TaskReward findByName(String name);
}
