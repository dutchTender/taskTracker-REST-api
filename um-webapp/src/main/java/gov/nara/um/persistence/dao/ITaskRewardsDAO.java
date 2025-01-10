package gov.nara.um.persistence.dao;

import gov.nara.common.interfaces.IByNameApi;
import gov.nara.um.persistence.model.TaskReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskRewardsDAO extends JpaRepository<TaskReward, Long>, JpaSpecificationExecutor<TaskReward>, IByNameApi<TaskReward> {
    public TaskReward findByName(String name);
}
