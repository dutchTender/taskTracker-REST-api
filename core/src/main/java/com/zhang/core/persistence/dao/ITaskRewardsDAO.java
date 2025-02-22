package com.zhang.core.persistence.dao;

import com.zhang.common.interfaces.generics.IByNameApi;
import com.zhang.core.persistence.model.TaskReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskRewardsDAO extends JpaRepository<TaskReward, Long>, JpaSpecificationExecutor<TaskReward>, IByNameApi<TaskReward> {
    public TaskReward findByName(String name);
}
