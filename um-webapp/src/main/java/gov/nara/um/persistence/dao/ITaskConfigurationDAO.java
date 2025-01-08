package gov.nara.um.persistence.dao;

import gov.nara.common.interfaces.IByNameApi;
import gov.nara.um.persistence.model.TaskConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskConfigurationDAO extends JpaRepository<TaskConfiguration, Long>, JpaSpecificationExecutor<TaskConfiguration>, IByNameApi<TaskConfiguration> {
    public TaskConfiguration findByName(String name);
}
